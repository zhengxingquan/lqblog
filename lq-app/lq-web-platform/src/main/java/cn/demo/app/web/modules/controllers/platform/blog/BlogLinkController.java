package cn.demo.app.web.modules.controllers.platform.blog;

import cn.demo.app.blog.modiles.models.Blog_link;
import cn.demo.app.blog.modules.services.BlogLinkService;
import cn.demo.app.web.commons.slog.annotation.SLog;
import cn.demo.framework.base.Result;
import cn.demo.framework.page.datatable.DataTableColumn;
import cn.demo.framework.page.datatable.DataTableOrder;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
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
@At("/platform/blog/link")
public class BlogLinkController {

    private static final Log log = Logs.get();

    @Inject
    @Reference
    private BlogLinkService blogLinkService;

    @At("/")
    @Ok("beetl:/platform/blog/link/index.html")
    @RequiresPermissions("blog.manager.link")
    public void index() {

    }

    @At("/add")
    @Ok("beetl:/platform/blog/link/add.html")
    @RequiresPermissions("blog.manager.link")
    public void add() {
    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("blog.manager.link.add")
    @SLog(tag = "添加博客友情链接", msg = "链接名称:${args[0].sortName}")
    public Object addDo(@Param("..") Blog_link blogLink) {
        try {
            blogLinkService.insert(blogLink);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/edit")
    @Ok("beetl:/platform/blog/link/edit.html")
    @RequiresPermissions("blog.manager.link.edit")
    public void edit(@Param("uuid") String uuid, HttpServletRequest req) {
        req.setAttribute("link", blogLinkService.fetch(uuid));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("blog.manager.link.edit")
    @SLog(tag = "修改博客友情链接", msg = "链接名称:${args[0].sortName}")
    public Object editDo(@Param("..") Blog_link blogLink) {
        try {
            blogLink.setUpAt(blogLink.now());
            blogLinkService.update(blogLink);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/delete/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.link.delete")
    @SLog(tag = "删除博客友情链接", msg = "链接UUID:${args[0]}")
    public Object delete(String uuid) {
        try {
            Blog_link blogLink = blogLinkService.fetch(uuid);
            if (blogLink == null) {
                return Result.success("system.noexists");
            }
            blogLink.setUpAt(blogLink.now());
            blogLinkService.vDelete(blogLink.getId());
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/delete")
    @Ok("json")
    @RequiresPermissions("blog.manager.link.delete")
    @SLog(tag = "删除博客友情链接", msg = "链接UUID:${args[0]}")
    public Object deletes(@Param("ids") String[] uuids) {
        try {

            if (uuids == null ||
                    uuids.length < 1) {
                return Result.success("system.noexists");
            }
            blogLinkService.vDelete(uuids);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/enable/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.link.enable")
    @SLog(tag = "启用博客友情链接", msg = "链接UUID:${args[0]}")
    public Object enable(String uuid) {
        try {
            Blog_link blogLink = blogLinkService.fetch(uuid);
            if (blogLink == null) {
                return Result.success("system.noexists");
            }
            blogLink.setUpAt(blogLink.now());
            blogLink.setShow(true);
            blogLinkService.update(blogLink, "^show|upAt$");
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/disable/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.link.disable")
    @SLog(tag = "隐藏博客友情链接", msg = "链接UUID:${args[0]}")
    public Object disable(String uuid) {
        try {
            Blog_link blogLink = blogLinkService.fetch(uuid);
            if (blogLink == null) {
                return Result.success("system.noexists");
            }
            blogLink.setShow(false);
            blogLinkService.update(blogLink, "^show|upAt$");
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At
    @Ok("json") // 忽略password和createAt属性,忽略空属性的json输出
    @RequiresPermissions("blog.manager.link")
    public Object data(@Param("name") String name, @Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.where("delFlag", "=", 0);
        if (Strings.isNotBlank(name)) {
            cnd.and("sortName", "like", "%" + name + "%");
        }
        return blogLinkService.data(length, start, draw, order, columns, cnd, null);
    }
}
