package cn.demo.app.wx.modules.services.impl;

import cn.demo.app.wx.modules.models.Wx_mass_news;
import cn.demo.app.wx.modules.services.WxMassNewsService;
import cn.demo.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=WxMassNewsService.class)
public class WxMassNewsServiceImpl extends BaseServiceImpl<Wx_mass_news> implements WxMassNewsService {
    public WxMassNewsServiceImpl(Dao dao) {
        super(dao);
    }
}
