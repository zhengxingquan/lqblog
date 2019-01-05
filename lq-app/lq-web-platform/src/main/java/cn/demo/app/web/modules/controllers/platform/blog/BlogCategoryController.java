package cn.demo.app.web.modules.controllers.platform.blog;

import cn.demo.app.blog.modiles.models.Blog_part;
import cn.demo.app.blog.modules.services.BlogCategoryService;
import cn.demo.app.web.commons.slog.annotation.SLog;
import cn.demo.app.web.commons.utils.StringUtil;
import cn.demo.app.web.commons.vo.blog.TreeVo;
import cn.demo.framework.base.Result;
import cn.demo.framework.page.datatable.DataTableColumn;
import cn.demo.framework.page.datatable.DataTableOrder;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2018/7/7-17:55
 * @desc : 输入描述
 **/
@IocBean
@At("/platform/blog/category")
public class BlogCategoryController {

    private static final Log log = Logs.get();

    @Inject
    @Reference
    private BlogCategoryService blogCategoryService;

    @At("/")
    @Ok("beetl:/platform/blog/category/index.html")
    @RequiresPermissions("blog.manager.category")
    public void index() {

    }

    @At("/add")
    @Ok("beetl:/platform/blog/category/add.html")
    @RequiresPermissions("blog.manager.category")
    public void add(@Param("uuid") String uuid , HttpServletRequest req) {
        if(Strings.isNotBlank(uuid)){
            req.setAttribute("part" ,  blogCategoryService.fetch(uuid));
        }
    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("blog.manager.category.add")
    @SLog(tag = "添加博客栏目", msg = "栏目名称:${args[0].name}")
    public Object addDo(@Param("..") Blog_part blogpart) {
        try {
            blogCategoryService.insert(blogpart);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/edit")
    @Ok("beetl:/platform/blog/category/edit.html")
    @RequiresPermissions("blog.manager.category.edit")
    public void edit(@Param("uuid") String uuid, HttpServletRequest req) {
        req.setAttribute("part",
                blogCategoryService.fetchLinks(blogCategoryService.fetch(uuid),"^parentPart$"));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("blog.manager.category.edit")
    @SLog(tag = "编辑博客栏目", msg = "栏目名称:${args[0].name}")
    public Object editDo(@Param("..") Blog_part blogPart) {
        try {
            blogPart.setUpAt(Times.getTS());
            blogPart.setUpBy(StringUtil.getPlatformUid());
            blogCategoryService.updateIgnoreNull(blogPart);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/delete/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.category.delete")
    @SLog(tag = "删除博客栏目", msg = "栏目编号:${args[0]}")
    public Object delete(String uuid) {
        try {
            Blog_part blogPart = blogCategoryService.fetch(uuid);
            if (blogPart == null) {
                return Result.success("system.noexists");
            }
            blogPart.setDelFlag(true);
            blogPart.setUpAt(Times.getTS());
            blogPart.setUpBy(StringUtil.getPlatformUid());
            blogCategoryService.update(blogPart,"^delFlag|upAt|upBy$");
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/delete")
    @Ok("json")
    @RequiresPermissions("blog.manager.link.delete")
    @SLog(tag = "批量删除博客栏目", msg = "栏目编号:${args[0]}")
    public Object deletes(@Param("ids") String[] uuids) {
        try {
            if (uuids == null || uuids.length < 1) {
                return Result.success("system.noexists");
            }
            blogCategoryService.vDelete(uuids);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/enable/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.category.enable")
    @SLog(tag = "启用博客栏目", msg = "栏目编号:${args[0]}")
    public Object enable(String uuid) {
        try {
            Blog_part blogPart = blogCategoryService.fetch(uuid);
            if (blogPart == null) {
                return Result.success("system.noexists");
            }
            blogPart.setUpAt(blogPart.now());
            blogPart.setUpBy(StringUtil.getPlatformUid());
            blogPart.setShow(true);
            blogCategoryService.update(blogPart, "^upBy|show|upAt$");
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/disable/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.category.disable")
    @SLog(tag = "禁用博客栏目", msg = "栏目编号:${args[0]}")
    public Object disable(String uuid) {
        try {
            Blog_part blogPart = blogCategoryService.fetch(uuid);
            if (blogPart == null) {
                return Result.success("system.noexists");
            }
            blogPart.setShow(false);
            blogPart.setUpAt(blogPart.now());
            blogPart.setUpBy(StringUtil.getPlatformUid());
            blogCategoryService.update(blogPart, "^upBy|show|upAt$");
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/tree")
    @Ok("json")
    @RequiresPermissions("blog.manager.category")
    public Object tree( @Param("pid") String uuid) {
        Cnd cnd = Cnd.where("parentId" , Strings.isNotBlank(uuid) ? "=" : "IS" , uuid);
        List<Blog_part> list = blogCategoryService.query(cnd);
        List<TreeVo> tree = new ArrayList<>();
        for (Blog_part part : list) {
            tree.add(new TreeVo(part.getId() , part.getName() , part.isHasChildren()));
        }
        return tree;
    }

    @At
    @Ok("json") // 忽略password和createAt属性,忽略空属性的json输出
    @RequiresPermissions("blog.manager.category")
    public Object data(@Param("name") String name, @Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.where("delFlag", "=", 0);
        if (Strings.isNotBlank(name)) {
            cnd.and("sortName", "like", "%" + name + "%");
        }
        return blogCategoryService.data(length, start, draw, order, columns, cnd, null);
    }
}
