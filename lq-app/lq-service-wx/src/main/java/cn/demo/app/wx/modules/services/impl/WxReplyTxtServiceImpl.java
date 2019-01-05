package cn.demo.app.wx.modules.services.impl;

import cn.demo.app.wx.modules.models.Wx_reply_txt;
import cn.demo.app.wx.modules.services.WxReplyTxtService;
import cn.demo.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=WxReplyTxtService.class)
public class WxReplyTxtServiceImpl extends BaseServiceImpl<Wx_reply_txt> implements WxReplyTxtService {
    public WxReplyTxtServiceImpl(Dao dao) {
        super(dao);
    }
}
