package cn.demo.app.wx.modules.services.impl;

import cn.demo.app.wx.modules.models.Wx_reply;
import cn.demo.app.wx.modules.services.WxReplyService;
import cn.demo.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=WxReplyService.class)
public class WxReplyServiceImpl extends BaseServiceImpl<Wx_reply> implements WxReplyService {
    public WxReplyServiceImpl(Dao dao) {
        super(dao);
    }
}
