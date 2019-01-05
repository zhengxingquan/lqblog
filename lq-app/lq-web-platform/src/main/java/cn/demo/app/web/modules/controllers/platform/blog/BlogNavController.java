package cn.demo.app.web.modules.controllers.platform.blog;

import cn.demo.app.blog.modiles.models.Blog_nav;
import cn.demo.app.blog.modules.services.BlogNavService;
import cn.demo.app.web.commons.slog.annotation.SLog;
import cn.demo.framework.base.Result;
import cn.demo.framework.page.datatable.DataTableColumn;
import cn.demo.framework.page.datatable.DataTableOrder;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.WhaleAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2018/7/7-17:55
 * @desc : 输入描述
 **/
@IocBean
@At("/platform/blog/nav")
public class BlogNavController {

    private static final Log log = Logs.get();

    @Inject
    @Reference
    private BlogNavService blogNavService;

    @At("/")
    @Ok("beetl:/platform/blog/nav/index.html")
    @RequiresPermissions("blog.manager.nav")
    public void index() {

    }

    @At("/add")
    @Ok("beetl:/platform/blog/nav/add.html")
    @RequiresPermissions("blog.manager.nav")
    public void add() {
    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("blog.manager.nav.add")
    @SLog(tag = "添加博客轮播图")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object addDo(@Param("..") Blog_nav blogNav) {
        try {
            blogNavService.insert(blogNav);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/edit")
    @Ok("beetl:/platform/blog/nav/edit.html")
    @RequiresPermissions("blog.manager.nav.edit")
    public void edit(@Param("uuid") String uuid, HttpServletRequest req) {
        req.setAttribute("nav", blogNavService.fetch(uuid));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("blog.manager.nav.edit")
    @SLog(tag = "修改博客轮播图")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object editDo(@Param("..") Blog_nav blogNav) {
        try {
            blogNav.setUpAt(blogNav.now());
            blogNavService.update(blogNav);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/delete/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.nav.delete")
    @SLog(tag = "删除博客轮播图")
    public Object delete(String uuid) {
        try {
            Blog_nav blogNav = blogNavService.fetch(uuid);
            if (blogNav == null) {
                return Result.success("system.noexists");
            }
            blogNav.setUpAt(blogNav.now());
            blogNavService.vDelete(blogNav.getId());
            blogNavService.update(blogNav, "^upAt$");
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/delete")
    @Ok("json")
    @RequiresPermissions("blog.manager.link.delete")
    @SLog(tag = "删除博客轮播图")
    public Object deletes(@Param("ids") String[] uuids) {
        try {
            if (uuids == null || uuids.length < 1) {
                return Result.success("system.noexists");
            }
            blogNavService.vDelete(uuids);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/enable/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.nav.enable")
    @SLog(tag = "启用博客轮播图", msg = "轮播图UUID:${args[0]}")
    public Object enable(String uuid) {
        try {
            Blog_nav blogNav = blogNavService.fetch(uuid);
            if (blogNav == null) {
                return Result.success("system.noexists");
            }
            blogNav.setUpAt(blogNav.now());
            blogNav.setShow(true);
            blogNavService.update(blogNav, "^show|upAt$");
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/disable/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.nav.disable")
    @SLog(tag = "隐藏博客轮播图", msg = "轮播图UUID:${args[0]}")
    public Object disable(String uuid) {
        try {
            Blog_nav blogNav = blogNavService.fetch(uuid);
            if (blogNav == null) {
                return Result.success("system.noexists");
            }
            blogNav.setShow(false);
            blogNav.setUpAt(blogNav.now());
            blogNavService.update(blogNav, "^show|upAt$");
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At
    @Ok("json") // 忽略password和createAt属性,忽略空属性的json输出
    @RequiresPermissions("blog.manager.nav")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.where("delFlag", "=", 0);
        return blogNavService.data(length, start, draw, order, columns, cnd, null);
    }
}
