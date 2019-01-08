package cn.demo.app.sys.commons.core;


import cn.demo.app.utils.InitSysMenusUtil;
import org.nutz.boot.NbApp;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.Modules;

/**
 * Created by demo on 2018/3/16.
 */
@IocBean(create = "init", depose = "depose")
@Modules(packages = "cn.demo")
public class DubboRpcSysMainLauncher {
    private static final Log log = Logs.get();

    @Inject
    private Dao dao;
    /**
     *初始化 系统配置表配置
     */
    private InitSysMenusUtil initSysMenusUtil = new InitSysMenusUtil();

    public static void main(String[] args) throws Exception {
        NbApp nb = new NbApp().setArgs(args).setPrintProcDoc(true);
        nb.getAppContext().setMainPackage("cn.demo");
        nb.run();
    }

    public void init() {
        initSysMenusUtil.initSys(dao);
    }

    public void depose() {
        initSysMenusUtil.depose();
    }
}
