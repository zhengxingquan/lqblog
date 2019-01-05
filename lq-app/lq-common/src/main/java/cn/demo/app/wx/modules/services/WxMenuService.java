package cn.demo.app.wx.modules.services;

import cn.demo.app.wx.modules.models.Wx_menu;
import cn.demo.framework.base.service.BaseService;

public interface WxMenuService extends BaseService<Wx_menu> {
    void save(Wx_menu menu, String pid);

    void deleteAndChild(Wx_menu menu);
}
