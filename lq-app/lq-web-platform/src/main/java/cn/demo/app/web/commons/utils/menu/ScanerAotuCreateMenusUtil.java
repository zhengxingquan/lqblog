package cn.demo.app.web.commons.utils.menu;

import cn.demo.app.sys.modules.models.Sys_menu;
import cn.demo.app.web.commons.core.WebPlatformMainLauncher;
import cn.demo.app.web.commons.enums.MenuType;
import cn.demo.app.web.commons.slog.annotation.SMenu;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Modules;
import org.nutz.resource.Scans;

import java.lang.reflect.Method;
import java.text.Collator;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author 黄川 306955302@qq.com
 * @date: 2018/5/14
 * 描述此类：
 */
public class ScanerAotuCreateMenusUtil {

    private final static Comparator CHINA_COMPARE = Collator.getInstance(java.util.Locale.CHINA);
    private List<Sys_menu> menuList = new ArrayList<>();

    /**
     * 读取菜单
     * @return
     */
    private List<Sys_menu> scanPackage() {
        for (String scanPackage : WebPlatformMainLauncher.class.getAnnotation(Modules.class).packages()) {
            List<Class<?>> classList = Scans.me().scanPackage(scanPackage);
            classList.stream().filter(aClass -> aClass.getAnnotation(At.class) != null).forEach(aClass -> scanClass(aClass));
        }
        Collections.sort(menuList, (o1, o2) -> CHINA_COMPARE.compare(o1.getName(), o2.getName()));
        return createTree(menuList, "");
    }


    /**
     * 插入自动扫描的菜单 (调用方法)
     *
     * @param dao
     * @param cleanData  是否重新新建
     */
    public void saveAutoScanMenus(Dao dao, boolean cleanData) {
        List<Sys_menu> menuList = scanPackage();
        if (cleanData) {
            dao.create(Sys_menu.class, true);
            saveChildren(dao, menuList);
        } else {
            saveChildren2(dao, menuList);
        }
    }

    /**
     * 初始化菜单
     *
     * @param dao
     * @param childrens
     */
    private void saveChildren(Dao dao, List<Sys_menu> childrens) {
        childrens = dao.insert(childrens);
        List<Sys_menu> update = new ArrayList<>();
        childrens.forEach(menu -> {
            if (Strings.isNotBlank(menu.getParentPermission())) {
                Sys_menu menu0 = dao.fetch(Sys_menu.class, Cnd.where("permission", "=", menu.getParentPermission()));
                menu.setParentId(menu0.getId());
                update.add(menu);
            }
            if (menu.getButtons().size() > 0) {
                saveChildren(dao, menu.getButtons());
            }
        });
        dao.update(update);
    }

    /**
     * 忽略已有菜单
     *
     * @param dao
     * @param menuList
     */
    private void saveChildren2(Dao dao, List<Sys_menu> menuList) {
        menuList.forEach(menu -> {
            List<Sys_menu> newChild = new ArrayList<>();
            List<Sys_menu> oldChild = new ArrayList<>();
            Sys_menu menu0 = dao.fetch(Sys_menu.class, Cnd.where("permission", "=", menu.getPermission()));
            if (menu0 == null) {
                dao.insert(menu);
            } else {
                menu.setId(menu0.getId());
                menu.setParentId(menu0.getParentId());
            }
            menu.getButtons().forEach(menu1 -> {
                Sys_menu menu2 = dao.fetch(Sys_menu.class, Cnd.where("permission", "=", menu1.getPermission()));
                if (menu2 == null) {
                    menu1.setParentId(menu.getId());
                    newChild.add(menu1);
                } else {
                    menu2.setButtons(menu1.getButtons());
                    oldChild.add(menu2);
                }
            });
            dao.insert(newChild);
            menu.setButtons(newChild);
            menu.getButtons().addAll(oldChild);
            if (menu.getButtons() != null && menu.getButtons().size() > 0) {
                saveChildren2(dao, menu.getButtons());
            }
        });
    }


    /**
     * 迭代权限树
     *
     * @param menus
     * @param parentPermission
     * @return
     */
    private List<Sys_menu> createTree(List<Sys_menu> menus, String parentPermission) {
        List<Sys_menu> childList = new ArrayList<>();
        for (Sys_menu c : menus) {
            String permission = c.getPermission();
            String pid = c.getParentPermission();
            if (parentPermission.equals(pid)) {
                List<Sys_menu> childs = createTree(menus, permission);
                c.setButtons(childs);
                childList.add(c);
            }
        }
        return childList;
    }


    /**
     * 取得子菜单
     *
     * @param menus
     * @param permission
     * @return
     */
    private Sys_menu getChilds(List<Sys_menu> menus, String permission) {
        Sys_menu menu = null;
        sw:
        for (Sys_menu p : menus) {
            if (p.getPermission().equals(permission)) {
                menu = p;
                break sw;
            } else if (p.getButtons() != null && p.getButtons().size() > 0) {
                menu = getChilds(p.getButtons(), permission);
                if (menu != null) {
                    break sw;
                }
            }
        }
        return menu;
    }


    private void scanClass(Class<?> klass) {
        Method[] methods = klass.getMethods();
        // 类名称
        String classAtPath = getAtPath(klass.getAnnotation(At.class), klass, null);
        for (Method method : methods) {
            SMenu autoMenuAuth = method.getAnnotation(SMenu.class);
            RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
            At methodAt = method.getAnnotation(At.class);
            if (autoMenuAuth == null) {
                continue;
            }
            if (autoMenuAuth != null && requiresPermissions == null) {
                throw new RuntimeException(MessageFormat.format("{0} 请设置@RequiresPermissions", method.toGenericString()));
            }
            String atPath = classAtPath + getAtPath(methodAt, null, method);
            Sys_menu menu = new Sys_menu();
            menu.setName(autoMenuAuth.name());
            if (MenuType.menu.equals(autoMenuAuth.type())) {
                menu.setHref(atPath);
            }
            menu.setType(autoMenuAuth.type().getValue());
            menu.setAliasName(autoMenuAuth.aliasName());
            menu.setShowit(true);
            menu.setDisabled(false);
            menu.setParentId(null);
            menu.setIcon(autoMenuAuth.icon());
            menu.setNote(autoMenuAuth.note());
            menu.setPermission(requiresPermissions.value()[0]);
            menu.setParentPermission(autoMenuAuth.parentPermission());
            menu.setOpAt(Times.getTS());
            menuList.add(menu);
        }
    }

    private String getAtPath(At at, Class<?> klass, Method method) {
        if (at == null || at.value().length == 0) {
            if (klass == null) {
                return Strings.lowerFirst(method.getName());
            } else {
                return Strings.lowerFirst(klass.getSimpleName());
            }
        } else {
            return at.value()[0];
        }
    }
}
