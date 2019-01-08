package cn.demo.app.web.modules.controllers.open.file;

import cn.demo.app.sys.modules.models.Sys_file;
import cn.demo.app.web.commons.base.Globals;
import cn.demo.app.web.commons.utils.DateUtil;
import cn.demo.framework.base.Result;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.impl.AdaptorErrorContext;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

/**
 * Created by Wizzer on 2016/7/5.
 */
@IocBean
@At("/open/file/upload")
public class UploadController {

    private static final Log log = Logs.get();

    @AdaptBy(type = UploadAdaptor.class, args = {"ioc:fileUpload"})
    @POST
    @At
    @Ok("json")
    @RequiresAuthentication
    //AdaptorErrorContext必须是最后一个参数
    public Result file(@Param("Filedata") TempFile tf, HttpServletRequest req, AdaptorErrorContext err) {
        try {
            if (err != null && err.getAdaptorErr() != null) {
                return  Result.error("file.error");
            } else if (tf == null) {
                return Result.error("file.empty");
            } else {
                Sys_file sys_file = new Sys_file();
                String s = tf.getSubmittedFileName().substring(tf.getSubmittedFileName().indexOf(".") + 1);
                String uri = "/file/" + DateUtil.format(new Date(), "yyyyMMdd") + "/" + R.UU32() + tf.getSubmittedFileName().substring(tf.getSubmittedFileName().indexOf("."));
                String f = Globals.AppUploadPath + uri;
                Files.write(new File(f), tf.getInputStream());
                return Result.success("file.upload.success", NutMap.NEW().addv("file_type", s.toLowerCase()).addv("file_name", tf.getName()).addv("file_size", tf.getSize()).addv("file_url", Globals.AppUploadBase + uri));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("system.option.error");
        } catch (Throwable e) {
            return Result.error("file.format.error");
        }
    }

    @AdaptBy(type = UploadAdaptor.class, args = {"ioc:videoUpload"})
    @POST
    @At
    @Ok("json")
    @RequiresAuthentication
    //AdaptorErrorContext必须是最后一个参数
    public Result video(@Param("Filedata") TempFile tf, HttpServletRequest req, AdaptorErrorContext err) {
        try {
            if (err != null && err.getAdaptorErr() != null) {
                return Result.error("file.error");
            } else if (tf == null) {
                return Result.error("file.empty");
            } else {
                String s = tf.getSubmittedFileName().substring(tf.getSubmittedFileName().indexOf(".") + 1);
                String uri = "/video/" + DateUtil.format(new Date(), "yyyyMMdd") + "/" + R.UU32() + tf.getSubmittedFileName().substring(tf.getSubmittedFileName().indexOf("."));
                String f = Globals.AppUploadPath + uri;
                Files.write(new File(f), tf.getInputStream());
                return Result.success("file.upload.success", NutMap.NEW().addv("file_type", s.toLowerCase()).addv("file_name", tf.getName()).addv("file_size", tf.getSize()).addv("file_url", Globals.AppUploadBase + uri));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("system.option.error");
        } catch (Throwable e) {
            return Result.error("file.video.format.error");
        }
    }

    @AdaptBy(type = UploadAdaptor.class, args = {"ioc:imageUpload"})
    @POST
    @At
    @Ok("json")
    @RequiresAuthentication
    //AdaptorErrorContext必须是最后一个参数
    public Result image(@Param("Filedata") TempFile tf, HttpServletRequest req, AdaptorErrorContext err) {
        try {
            if (err != null && err.getAdaptorErr() != null) {
                return Result.error("file.error");
            } else if (tf == null) {
                return Result.error("file.empty");
            } else {
                String uri = "/image/" + DateUtil.format(new Date(), "yyyyMMdd") + "/" + R.UU32() + tf.getSubmittedFileName().substring(tf.getSubmittedFileName().indexOf("."));
                String f = Globals.AppUploadPath + uri;
                Files.write(new File(f), tf.getInputStream());
                return Result.success("file.upload.success", Globals.AppUploadBase + uri);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error("system.option.error");
        } catch (Throwable e) {
            return Result.error("file.image.format.empty");
        }
    }
}
