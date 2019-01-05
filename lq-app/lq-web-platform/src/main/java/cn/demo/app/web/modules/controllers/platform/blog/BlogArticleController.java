package cn.demo.app.web.modules.controllers.platform.blog;

import cn.demo.app.blog.modiles.models.Blog_article;
import cn.demo.app.blog.modules.services.BlogArticleService;
import cn.demo.app.web.commons.slog.annotation.SLog;
import cn.demo.app.web.commons.utils.DateUtil;
import cn.demo.app.web.commons.utils.StringUtil;
import cn.demo.app.web.commons.vo.blog.ArticleVo;
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
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2018/7/7-17:55
 * @desc : 文章
 **/
@IocBean
@At("/platform/blog/article")
public class BlogArticleController {

    private static final Log log = Logs.get();

    @Inject
    @Reference
    private BlogArticleService blogarticleService;

    @At("/")
    @Ok("beetl:/platform/blog/article/index.html")
    @RequiresPermissions("blog.manager.article")
    public void index() {

    }

    @At("/add")
    @Ok("beetl:/platform/blog/article/add.html")
    @RequiresPermissions("blog.manager.article")
    public void add() {
    }

    @At("/addDo")
    @Ok("json")
    @RequiresPermissions("blog.manager.article.add")
    @SLog(tag = "添加博客文章", msg = "文章名称:${args[0].name}")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object addDo(@Param("..") Blog_article blogarticle , @Param("startTime") String startTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            blogarticle.setPubTime(Times.parse(sdf, startTime).getTime() / 1000);
            blogarticleService.insert(blogarticle);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/edit")
    @Ok("beetl:/platform/blog/article/edit.html")
    @RequiresPermissions("blog.manager.article.edit")
    public void edit(@Param("uuid") String uuid, HttpServletRequest req) {
        req.setAttribute("article", blogarticleService.fetchLinks(blogarticleService.fetch(uuid),"^part$"));
    }

    @At("/editDo")
    @Ok("json")
    @RequiresPermissions("blog.manager.article.edit")
    @SLog(tag = "修改博客文章", msg = "文章名称:${args[0].name}")
    @AdaptBy(type = WhaleAdaptor.class)
    public Object editDo(@Param("..") Blog_article blogarticle) {
        try {
            blogarticle.setUpAt(Times.getTS());
            blogarticle.setUpBy(StringUtil.getPlatformUid());
            blogarticleService.updateIgnoreNull(blogarticle);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/delete/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.article.delete")
    @SLog(tag = "删除博客文章" , msg = "文章编号:${args[0]}")
    public Object delete(String uuid) {
        try {
            Blog_article blogarticle = blogarticleService.fetch(uuid);
            if (blogarticle == null) {
                return Result.success("system.noexists");
            }
            blogarticle.setUpAt(Times.getTS());
            blogarticle.setUpBy(StringUtil.getPlatformUid());
            blogarticle.setDelFlag(true);
            blogarticleService.updateIgnoreNull(blogarticle);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/delete")
    @Ok("json")
    @RequiresPermissions("blog.manager.link.delete")
    @SLog(tag = "删除博客文章" , msg = "文章编号:${args[0]}")
    public Object deletes(@Param("ids") String[] uuids) {
        try {
            if (uuids == null || uuids.length < 1) {
                return Result.success("system.noexists");
            }
            blogarticleService.vDelete(uuids);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/enable/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.article.enable")
    @SLog(tag = "启用博客文章", msg = "文章编号:${args[0]}")
    public Object enable(String uuid) {
        try {
            Blog_article blogarticle = blogarticleService.fetch(uuid);
            if (blogarticle == null) {
                return Result.success("system.noexists");
            }
            blogarticle.setUpAt(blogarticle.now());
            blogarticle.setUpBy(StringUtil.getPlatformUid());
            blogarticle.setShow(true);
            blogarticleService.update(blogarticle, "^upBy|show|upAt$");
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/disable/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.article.disable")
    @SLog(tag = "隐藏博客文章", msg = "文章编号:${args[0]}")
    public Object disable(String uuid) {
        try {
            Blog_article blogarticle = blogarticleService.fetch(uuid);
            if (blogarticle == null) {
                return Result.success("system.noexists");
            }
            blogarticle.setShow(false);
            blogarticle.setUpBy(StringUtil.getPlatformUid());
            blogarticle.setUpAt(blogarticle.now());
            blogarticleService.update(blogarticle, "^upBy|show|upAt$");
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At("/recommend/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.article.recommend")
    @SLog(tag = "推荐博客文章", msg = "文章编号:${args[0]}")
    public Object recommend(String uuid) {
        try {
            Blog_article blogarticle = blogarticleService.fetch(uuid);
            if (blogarticle == null) {
                return Result.success("system.noexists");
            }
            blogarticle.setUpAt(blogarticle.now());
            blogarticle.setRecommend(true);
            blogarticle.setUpBy(StringUtil.getPlatformUid());
            blogarticleService.update(blogarticle, "^upBy|recommend|upAt$");
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/noRecommend/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.article.recommend")
    @SLog(tag = "取消推荐博客文章", msg = "文章编号:${args[0]}")
    public Object noRecommend(String uuid) {
        try {
            Blog_article blogarticle = blogarticleService.fetch(uuid);
            if (blogarticle == null) {
                return Result.success("system.noexists");
            }
            blogarticle.setUpAt(blogarticle.now());
            blogarticle.setRecommend(false);
            blogarticle.setUpBy(StringUtil.getPlatformUid());
            blogarticleService.update(blogarticle, "^upBy|recommend|upAt$");
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/top/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.article.top")
    @SLog(tag = "置顶博客文章", msg = "文章编号:${args[0]}")
    public Object top(String uuid) {
        try {
            Blog_article blogarticle = blogarticleService.fetch(uuid);
            if (blogarticle == null) {
                return Result.success("system.noexists");
            }
            blogarticle.setUpAt(blogarticle.now());
            blogarticle.setTop(true);
            blogarticle.setUpBy(StringUtil.getPlatformUid());
            blogarticleService.update(blogarticle, "^upBy|top|upAt$");
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    @At("/noTop/?")
    @Ok("json")
    @RequiresPermissions("blog.manager.article.top")
    @SLog(tag = "取消推荐博客文章", msg = "文章编号:${args[0]}")
    public Object noTop(String uuid) {
        try {
            Blog_article blogarticle = blogarticleService.fetch(uuid);
            if (blogarticle == null) {
                return Result.success("system.noexists");
            }
            blogarticle.setUpAt(blogarticle.now());
            blogarticle.setTop(false);
            blogarticle.setUpBy(StringUtil.getPlatformUid());
            blogarticleService.update(blogarticle, "^upBy|top|upAt$");
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }


    @At
    @Ok("json") // 忽略password和createAt属性,忽略空属性的json输出
    @RequiresPermissions("blog.manager.article")
    public Object data(@Param("..") ArticleVo articleVo, @Param("length") int length, @Param("start") int start, @Param("draw") int draw, @Param("::order") List<DataTableOrder> order, @Param("::columns") List<DataTableColumn> columns) {
        Cnd cnd = Cnd.where("delFlag", "=", 0);
        if (Strings.isNotBlank(articleVo.getBeginDate())
                && Strings.isBlank(articleVo.getEndDate())) {
            cnd.and("pubTime", ">=", DateUtil.getYMDTime(articleVo.getName()));
        } else if (Strings.isNotBlank(articleVo.getEndDate())
                && Strings.isBlank(articleVo.getBeginDate())) {
            cnd.and("pubTime", "<=", DateUtil.getYMDTime(articleVo.getName()));
        } else if (Strings.isNotBlank(articleVo.getBeginDate())
                && Strings.isNotBlank(articleVo.getEndDate())) {
            cnd.and("pubTime", "between", new Object[]{DateUtil.getYMDTime(articleVo.getBeginDate()), DateUtil.getYMDTime(articleVo.getEndDate())});
        }
        if (articleVo.getTop() != null) {
            cnd.and("top", "=", articleVo.getTop());
        }
        if (articleVo.getRecommend() != null) {
            cnd.and("recommend", "=", articleVo.getRecommend());
        }
        if (articleVo.getShow() != null) {
            cnd.and("show", "=", articleVo.getShow());
        }
        if (Strings.isNotBlank(articleVo.getPartId())) {
            cnd.and("partId", "=", articleVo.getPartId());
        }
        if (Strings.isNotBlank(articleVo.getName())) {
            cnd.and("name", "=", "%" + articleVo.getName() + "%");
        }
        return blogarticleService.data(length, start, draw, order, columns, cnd, "^part$");
    }
}
