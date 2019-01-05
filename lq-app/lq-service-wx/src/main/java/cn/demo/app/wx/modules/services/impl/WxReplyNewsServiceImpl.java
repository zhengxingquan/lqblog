package cn.demo.app.wx.modules.services.impl;

import cn.demo.app.wx.modules.models.Wx_reply_news;
import cn.demo.app.wx.modules.services.WxReplyNewsService;
import cn.demo.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=WxReplyNewsService.class)
public class WxReplyNewsServiceImpl extends BaseServiceImpl<Wx_reply_news> implements WxReplyNewsService {
    public WxReplyNewsServiceImpl(Dao dao) {
        super(dao);
    }
}
