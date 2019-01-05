package cn.demo.app.web.modules.controllers.platform.blog;

import cn.demo.app.blog.modiles.models.Blog_record;
import cn.demo.app.blog.modules.services.BlogRecordService;
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
import org.nutz.lang.Times;
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
 * @desc : 博客的更新日志
 **/
@IocBean
@At("/platform/blog/record")
public class BlogRecordController {

    private static final Log log = Logs.get();

    @Inject
    @Reference
    private BlogRecordService blogRecordService;

    @At("/")
    @Ok("beetl:/platform/blog/record/index.html")
    @RequiresPermissions("blog.manager.record")
    public void index() {

    }

    @At("/add")
    @Ok("beetl:/platform/blog/record/add.html")
    @RequiresPermissions("blog.manager.record.add")
    public void add() {
    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("blog.manager.record.add")
    @SLog(tag = "添加更新记录", msg = "网站记录")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object addDo(@Param("..") Blog_record blogRecord) {
        try {
            blogRecordService.insert(blogRecord);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/edit")
    @Ok("beetl:/platform/blog/record/edit.html")
    @RequiresPermissions("blog.manager.record.edit")
    public void edit(@Param("uuid") String uuid, HttpServletRequest req) {
        if (Strings.isNotBlank(uuid)) {
            req.setAttribute("article", blogRecordService.fetch(uuid));
        }
        req.setAttribute("article", null);
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("blog.manager.record.edit")
    @SLog(tag = "更新记录", msg = "更新记录")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object editDo(@Param("..") Blog_record blogRecord) {
        try {
            blogRecord.setUpAt(Times.getTS());
            blogRecordService.updateIgnoreNull(blogRecord);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At
    @Ok("json") // 忽略password和createAt属性,忽略空属性的json输出
    @RequiresPermissions("blog.manager.record")
    public Object data(@Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        return blogRecordService.data(length, start, draw, order, columns, Cnd.where("delFlag", "=", 0), null);
    }
}
