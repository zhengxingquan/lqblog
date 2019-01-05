package cn.demo.app.web;


import cn.demo.app.sys.modules.services.SysConfigService;
import cn.demo.app.web.commons.core.WebPlatformMainLauncher;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.NbApp;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2019/1/5-17:43
 * @Description : 输入描述
 **/
@IocBean(create = "init")
@RunWith(NbJUnit4Runner.class)
public class WebDemoTest extends Assert {

    //先启动SYS模块
    @Inject
    @Reference
    private SysConfigService sysConfigService;

    public void init() {
        System.out.println("say hi");
    }

    @Test
    public void test_service_inject() {
        assertNotNull(sysConfigService);
        System.out.println("sys_config:::"+ Json.toJson(sysConfigService.getAllList()));
    }

    // 测试类可提供public的static的createNbApp方法,用于定制当前测试类所需要的NbApp对象.
    // 测试类带@IocBean或不带@IocBean,本规则一样生效
    // 若不提供,默认使用当前测试类作为MainLauncher.
    // 也可以自定义NbJUnit4Runner, 继承NbJUnit4Runner并覆盖其createNbApp方法
    public static NbApp createNbApp() {
        NbApp nb = new NbApp().setMainClass(WebPlatformMainLauncher.class).setPrintProcDoc(false);
        nb.getAppContext().setMainPackage("cn.demo");
        return nb;
    }
}
