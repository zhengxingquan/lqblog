package cn.demo.app.cms.modules.services.impl;


import cn.demo.app.cms.modules.models.Cms_link_class;
import cn.demo.app.cms.modules.services.CmsLinkClassService;
import cn.demo.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=CmsLinkClassService.class)
public class CmsLinkClassServiceImpl extends BaseServiceImpl<Cms_link_class> implements CmsLinkClassService {
    public CmsLinkClassServiceImpl(Dao dao) {
        super(dao);
    }
}
