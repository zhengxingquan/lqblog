package cn.demo.app.sys.modules.services.impl;

import cn.demo.app.sys.modules.models.Sys_route;
import cn.demo.app.sys.modules.services.SysRouteService;
import cn.demo.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * Created by demo on 2016/12/23.
 */
@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysRouteService.class)
public class SysRouteServiceImpl extends BaseServiceImpl<Sys_route> implements SysRouteService {
    public SysRouteServiceImpl(Dao dao) {
        super(dao);
    }
}