package cn.demo.app.sys.modules.services.impl;

import cn.demo.app.sys.modules.models.Sys_msg_user;
import cn.demo.app.sys.modules.services.SysMsgUserService;
import cn.demo.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysMsgUserService.class)
public class SysMsgUserServiceImpl extends BaseServiceImpl<Sys_msg_user> implements SysMsgUserService {
    public SysMsgUserServiceImpl(Dao dao) {
        super(dao);
    }
}
