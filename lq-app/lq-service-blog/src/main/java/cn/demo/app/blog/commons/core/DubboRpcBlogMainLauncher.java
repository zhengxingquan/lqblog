package cn.demo.app.blog.commons.core;

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
        initSysMenusUtil.depose();
    }
}
