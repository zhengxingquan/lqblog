package cn.demo.app.wx.modules.services.impl;

import cn.demo.app.wx.modules.models.Wx_user;
import cn.demo.app.wx.modules.services.WxUserService;
import cn.demo.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=WxUserService.class)
public class WxUserServiceImpl extends BaseServiceImpl<Wx_user> implements WxUserService {
    public WxUserServiceImpl(Dao dao) {
        super(dao);
    }
}
