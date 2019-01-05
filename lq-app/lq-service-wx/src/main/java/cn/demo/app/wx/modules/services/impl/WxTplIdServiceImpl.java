package cn.demo.app.wx.modules.services.impl;

import cn.demo.app.wx.modules.models.Wx_tpl_id;
import cn.demo.app.wx.modules.services.WxTplIdService;
import cn.demo.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=WxTplIdService.class)
public class WxTplIdServiceImpl extends BaseServiceImpl<Wx_tpl_id> implements WxTplIdService {
    public WxTplIdServiceImpl(Dao dao) {
        super(dao);
    }
}
