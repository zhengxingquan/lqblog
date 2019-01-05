package cn.demo.app.cms.modules.services.impl;

import cn.demo.app.cms.modules.models.Cms_article;
import cn.demo.app.cms.modules.services.CmsArticleService;
import cn.demo.framework.base.service.BaseServiceImpl;
import cn.demo.framework.page.Pagination;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.plugins.wkcache.annotation.CacheDefaults;
import org.nutz.plugins.wkcache.annotation.CacheRemoveAll;
import org.nutz.plugins.wkcache.annotation.CacheResult;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=CmsArticleService.class)
@CacheDefaults(cacheName = "cms_article")
public class CmsArticleServiceImpl extends BaseServiceImpl<Cms_article> implements CmsArticleService {
    public CmsArticleServiceImpl(Dao dao) {
        super(dao);
    }

    @Override
    @CacheResult
    public Pagination getListPage(int pageNumber, int pageSize, Condition cnd) {
        return this.listPage(pageNumber, pageSize, cnd);
    }

    @Override
    @CacheResult
    public Cms_article getArticle(Condition cnd) {
        return this.fetch(cnd);
    }

    @Override
    @CacheRemoveAll
    public void clearCache() {

    }
}
