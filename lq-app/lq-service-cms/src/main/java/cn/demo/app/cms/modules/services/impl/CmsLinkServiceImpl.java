package cn.demo.app.cms.modules.services.impl;


import cn.demo.app.cms.modules.models.Cms_link;
import cn.demo.app.cms.modules.models.Cms_link_class;
import cn.demo.app.cms.modules.services.CmsLinkClassService;
import cn.demo.app.cms.modules.services.CmsLinkService;
import cn.demo.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.plugins.wkcache.annotation.CacheDefaults;
import org.nutz.plugins.wkcache.annotation.CacheRemoveAll;
import org.nutz.plugins.wkcache.annotation.CacheResult;

import java.util.ArrayList;
import java.util.List;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass = CmsLinkService.class)
@CacheDefaults(cacheName = "cms_link")
public class CmsLinkServiceImpl extends BaseServiceImpl<Cms_link> implements CmsLinkService {
    public CmsLinkServiceImpl(Dao dao) {
        super(dao);
    }

    @Inject
    private CmsLinkClassService cmsLinkClassService;

    @Override
    @CacheResult
    public List<Cms_link> getLinkList(String code, int size) {
        List<Cms_link> links = new ArrayList<>();
        Cms_link_class cmsLinkClass = cmsLinkClassService.fetch(Cnd.where("code", "=", code));
        if (cmsLinkClass != null) {
            Pager pager = new Pager();
            pager.setPageSize(size);
            pager.setPageNumber(1);
            links = this.query(Cnd.where("classId", "=", cmsLinkClass.getId()).desc("opAt"), pager);
        }
        return links;
    }

    @Override
    @CacheRemoveAll
    public void clearCache() {

    }
}
