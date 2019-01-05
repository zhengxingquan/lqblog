package cn.demo.app.web.modules.controllers.platform.blog;

import cn.demo.app.blog.modiles.models.Blog_notice;
import cn.demo.app.blog.modules.services.BlogNoticeService;
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
@At("/platform/blog/notice")
public class BlogNoticeController {

    private static final Log log = Logs.get();

    @Inject
    @Reference
    private BlogNoticeService blogNoticeService;

    @At("/")
    @Ok("beetl:/platform/blog/notice/index.html")
    @RequiresPermissions("blog.manager.notice")
    public void index() {

    }

    @At("/add")
    @Ok("beetl:/platform/blog/notice/add.html")
    @RequiresPermissions("blog.manager.notice")
    public void add() {
    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("blog.manager.notice.add")
    @SLog(tag = "添加博客公告")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object addDo(@Param("..") Blog_notice blognotice) {
        try {
            blogNoticeService.insert(blognotice);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/edit")
    @Ok("beetl:/platform/blog/notice/edit.html")
    @RequiresPermissions("blog.manager.notice.edit")
    public void edit(@Param("uuid") String uuid, HttpServletRequest req) {
        req.setAttribute("notice", blogNoticeService.fetch(uuid));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("blog.manager.notice.edit")
    @SLog(tag = "修改博客公告")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object editDo(@Param("..") Blog_notice blognotice) {
        try {
            blognotice.setUpAt(blognotice.now());
            blogNoticeService.update(blognotice);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/delete/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.notice.delete")
    @SLog(tag = "删除博客公告")
    public Object delete(String uuid) {
        try {
            Blog_notice blognotice = blogNoticeService.fetch(uuid);
            if (blognotice == null) {
                return Result.success("system.noexists");
            }
            blognotice.setUpAt(blognotice.now());
            blogNoticeService.vDelete(blognotice.getId());
            blogNoticeService.update(blognotice, "^upAt$");
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/delete")
    @Ok("json")
    @RequiresPermissions("blog.manager.link.delete")
    @SLog(tag = "删除博客公告")
    public Object deletes(@Param("ids") String[] uuids) {
        try {
            if (uuids == null || uuids.length < 1) {
                return Result.success("system.noexists");
            }
            blogNoticeService.vDelete(uuids);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/enable/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.notice.enable")
    @SLog(tag = "启用博客公告", msg = "公告UUID:${args[0]}")
    public Object enable(String uuid) {
        try {
            Blog_notice blognotice = blogNoticeService.fetch(uuid);
            if (blognotice == null) {
                return Result.success("system.noexists");
            }
            blognotice.setUpAt(blognotice.now());
            blognotice.setShow(true);
            blogNoticeService.update(blognotice, "^show|upAt$");
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/disable/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.notice.disable")
    @SLog(tag = "隐藏博客公告", msg = "公告UUID:${args[0]}")
    public Object disable(String uuid) {
        try {
            Blog_notice blognotice = blogNoticeService.fetch(uuid);
            if (blognotice == null) {
                return Result.success("system.noexists");
            }
            blognotice.setShow(false);
            blognotice.setUpAt(blognotice.now());
            blogNoticeService.update(blognotice, "^show|upAt$");
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At
    @Ok("json") // 忽略password和createAt属性,忽略空属性的json输出
    @RequiresPermissions("blog.manager.notice")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.where("delFlag", "=", 0);
        return blogNoticeService.data(length, start, draw, order, columns, cnd, null);
    }
}
