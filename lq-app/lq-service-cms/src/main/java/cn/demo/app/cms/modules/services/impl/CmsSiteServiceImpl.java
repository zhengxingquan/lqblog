package cn.demo.app.cms.modules.services.impl;

import cn.demo.app.cms.modules.models.Cms_site;
import cn.demo.app.cms.modules.services.CmsSiteService;
import cn.demo.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.plugins.wkcache.annotation.CacheDefaults;
import org.nutz.plugins.wkcache.annotation.CacheRemoveAll;
import org.nutz.plugins.wkcache.annotation.CacheResult;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass = CmsSiteService.class)
@CacheDefaults(cacheName = "cms_site")
public class CmsSiteServiceImpl extends BaseServiceImpl<Cms_site> implements CmsSiteService {
    public CmsSiteServiceImpl(Dao dao) {
        super(dao);
    }

    @Override
    @CacheResult
    public Cms_site getSite(String code) {
        return this.fetch(Cnd.where("id", "=", code));
    }

    @Override
    @CacheRemoveAll
    public void clearCache() {

    }
}
