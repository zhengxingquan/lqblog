package cn.demo.app.web.commons.slog.annotation;

import cn.demo.app.web.commons.enums.MenuType;

import java.lang.annotation.*;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2019/1/17-20:12
 * @Description : 方法菜单注解
 **/

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SMenu {

    /**
     * 菜单名称
     * @return
     */
    String name() default "测试";

    /**
     *
     * @return
     */
    MenuType type() default MenuType.data;

    /**
     * 权限标识
     * @return
     */
    String  permission();

    /**
     *  图标
     */
    String icon() default "";

    /**
     *  备注
     */
    String note() default "";

    /**
     * 上级权限的标识
     * <p>
     * 留空表明自己是根节点
     *
     * @return
     */
    String parentPermission() default "";

    /**
     * 菜单别名
     * @return
     */
    String aliasName();

    /**
     * 打开方式
     * @return
     */
    String target() default "data-pjax";
}
