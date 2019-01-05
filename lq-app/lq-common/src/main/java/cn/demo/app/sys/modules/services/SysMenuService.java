package cn.demo.app.sys.modules.services;

import cn.demo.app.sys.modules.models.Sys_menu;
import cn.demo.framework.base.service.BaseService;
import org.nutz.lang.util.NutMap;

import java.util.List;

/**
 * Created by demo on 2016/12/22.
 */
public interface SysMenuService extends BaseService<Sys_menu> {
    /**
     * 保存菜单
     *
     * @param menu
     * @param pid
     */
    void save(Sys_menu menu, String pid, List<NutMap> datas);

    /**
     * 编辑菜单
     *
     * @param menu
     * @param pid
     */
    void edit(Sys_menu menu, String pid, List<NutMap> datas);

    /**
     * 级联删除菜单
     *
     * @param menu
     */
    void deleteAndChild(Sys_menu menu);
}
