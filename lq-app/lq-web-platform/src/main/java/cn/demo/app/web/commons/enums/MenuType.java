package cn.demo.app.web.commons.enums;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2019/1/17-21:00
 * @Description : 菜单按钮类型
 **/
public enum MenuType {
    //操作按钮
    data("data"),
    //菜单按钮
    menu("menu");

    private final String value;

    MenuType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
