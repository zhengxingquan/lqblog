package cn.demo.app.wx.modules.services.impl;


import cn.demo.app.wx.modules.models.Wx_config;
import cn.demo.app.wx.modules.services.WxConfigService;
import cn.demo.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;


@IocBean(args = {"refer:dao"})
@Service(interfaceClass=WxConfigService.class)
public class WxConfigServiceImpl extends BaseServiceImpl<Wx_config> implements WxConfigService {
    public WxConfigServiceImpl(Dao dao) {
        super(dao);
    }

}
