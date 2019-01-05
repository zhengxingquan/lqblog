package cn.demo.app.blog.commons.core;

import org.nutz.boot.NbApp;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.Modules;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2018/7/7-17:43
 * @desc : 输入描述
 **/
@IocBean(create = "init", depose = "depose")
@Modules(packages = "cn.demo")
public class DubboRpcBlogMainLauncher {

    private static final Log log = Logs.get();

    @Inject
    private Dao dao;

    public static void main(String[] args) throws Exception {
        NbApp nb = new NbApp().setArgs(args).setPrintProcDoc(true);
        nb.getAppContext().setMainPackage("cn.demo");
        nb.run();
    }

    public void init() {
        //通过POJO类创建表结构
        try {
            Daos.createTablesInPackage(dao, "cn.demo.app.blog", false);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        try {
            if (log.isDebugEnabled()) {
                //通过POJO类修改表结构
                Daos.migration(dao, "cn.demo.app.blog", true, false);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }


    public void depose() {
        // 非mysql数据库,或多webapp共享mysql驱动的话,以下语句删掉
        try {
            Mirror.me(Class.forName("com.mysql.jdbc.AbandonedConnectionCleanupThread")).invoke(null, "shutdown");
        } catch (Throwable e) {
        }
        // 解决com.alibaba.druid.proxy.DruidDriver和com.mysql.jdbc.Driver在reload时报warning的问题
        // 多webapp共享mysql驱动的话,以下语句删掉
        Enumeration<Driver> en = DriverManager.getDrivers();
        while (en.hasMoreElements()) {
            try {
                Driver driver = en.nextElement();
                String className = driver.getClass().getName();
                log.debug("deregisterDriver: " + className);
                DriverManager.deregisterDriver(driver);
            } catch (Exception e) {
            }
        }
    }
}
