package cn.demo.app.wx.modules.services.impl;


import cn.demo.app.wx.modules.models.Wx_mass_send;
import cn.demo.app.wx.modules.services.WxMassSendService;
import cn.demo.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=WxMassSendService.class)
public class WxMassSendServiceImpl extends BaseServiceImpl<Wx_mass_send> implements WxMassSendService {
    public WxMassSendServiceImpl(Dao dao) {
        super(dao);
    }
}
