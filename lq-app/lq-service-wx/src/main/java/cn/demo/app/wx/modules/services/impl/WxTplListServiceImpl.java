package cn.demo.app.wx.modules.services.impl;

import cn.demo.app.wx.modules.models.Wx_tpl_list;
import cn.demo.app.wx.modules.services.WxTplListService;
import cn.demo.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=WxTplListService.class)
public class WxTplListServiceImpl extends BaseServiceImpl<Wx_tpl_list> implements WxTplListService {
    public WxTplListServiceImpl(Dao dao) {
        super(dao);
    }
}
