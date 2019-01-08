package cn.demo.app.wx.commons.core;

import cn.demo.app.utils.InitSysMenusUtil;
import org.nutz.boot.NbApp;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.Modules;

/**
 * Created by wizzer on 2018/3/17.
 */
@IocBean(create = "init", depose = "depose")
@Modules(packages = "cn.demo")
public class DubboRpcWxMainLauncher {
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
        //通过POJO类创建表结构
        try {
            Daos.createTablesInPackage(dao, "cn.demo.app.wx", false);
            //通过POJO类修改表结构
            //Daos.migration(dao, "cn.demo.app.wx", true, false);
        } catch (Exception e) {
        }
    }

    public void depose() {
        initSysMenusUtil.depose();
    }
}
