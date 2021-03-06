package cn.demo.app.wx.modules.services.impl;

import cn.demo.app.wx.modules.models.Wx_tpl_log;
import cn.demo.app.wx.modules.services.WxTplLogService;
import cn.demo.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=WxTplLogService.class)
public class WxTplLogServiceImpl extends BaseServiceImpl<Wx_tpl_log> implements WxTplLogService {
    public WxTplLogServiceImpl(Dao dao) {
        super(dao);
    }
}
