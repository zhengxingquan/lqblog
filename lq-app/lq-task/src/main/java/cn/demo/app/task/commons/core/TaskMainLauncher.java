package cn.demo.app.task.commons.core;

import cn.demo.app.sys.modules.models.Sys_task;
import cn.demo.app.sys.modules.services.SysTaskService;
import cn.demo.app.task.commons.base.Globals;
import cn.demo.app.task.modules.services.TaskPlatformService;
import cn.demo.app.utils.InitSysMenusUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import org.nutz.boot.NbApp;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.Modules;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * Created by demo on 2018/3/19.
 */
@IocBean(create = "init", depose = "depose")
@Modules(packages = "cn.demo")
public class TaskMainLauncher {
    private static final Log log = Logs.get();
    @Inject
    private TaskPlatformService taskPlatformService;
    @Inject
    @Reference
    private SysTaskService sysTaskService;
    @Inject("refer:$ioc")
    private Ioc ioc;
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
        if (!dao.exists("sys_qrtz_triggers")) {
            //执行Quartz SQL脚本
            String dbType = dao.getJdbcExpert().getDatabaseType();
            log.debug("dbType:::" + dbType);
            FileSqlManager fmq = new FileSqlManager("quartz/" + dbType.toLowerCase() + ".sql");
            List<Sql> sqlListq = fmq.createCombo(fmq.keys());
            Sql[] sqlsq = sqlListq.toArray(new Sql[sqlListq.size()]);
            for (Sql sql : sqlsq) {
                dao.execute(sql);
            }
        }
        if (0 == sysTaskService.count()) {
            //定时任务示例
            Sys_task task = new Sys_task();
            task.setDisabled(true);
            task.setName("测试任务");
            task.setJobClass("cn.demo.app.task.commons.ext.quartz.job.TestJob");
            task.setCron("*/5 * * * * ?");
            task.setData("{\"hi\":\"Wechat:demo | send red packets of support,thank u\"}");
            task.setNote("微信号：demo | 欢迎发送红包以示支持，多谢。。");
            sysTaskService.insert(task);
        }
        ioc.get(Globals.class).init(sysTaskService);
    }

    public void depose() {
        initSysMenusUtil.depose();
        try {
            MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
            ObjectName objectName = new ObjectName("com.alibaba.druid:type=MockDriver");
            if (mbeanServer.isRegistered(objectName)) {
                mbeanServer.unregisterMBean(objectName);
            }
            objectName = new ObjectName("com.alibaba.druid:type=DruidDriver");
            if (mbeanServer.isRegistered(objectName)) {
                mbeanServer.unregisterMBean(objectName);
            }
        } catch (Exception ex) {
        }
    }
}
