package cn.demo.app.sys.modules.services;

import cn.demo.app.sys.modules.models.Sys_msg;
import cn.demo.framework.base.service.BaseService;

public interface SysMsgService extends BaseService<Sys_msg> {
    /**
     * 保存信息同时发送
     *
     * @param sysMsg
     * @param users
     */
    Sys_msg saveMsg(Sys_msg sysMsg, String[] users);

    /**
     * 删除消息及消息用户表数据
     *
     * @param id
     */
    void deleteMsg(String id);
}
