package cn.demo.app.sys.modules.services;

import cn.demo.app.sys.modules.models.Sys_unit;
import cn.demo.framework.base.service.BaseService;

/**
 * Created by demo on 2016/12/22.
 */
public interface SysUnitService extends BaseService<Sys_unit> {
    /**
     * 保存单位
     * @param unit
     * @param pid
     */
    void save(Sys_unit unit, String pid);

    /**
     * 级联删除单位及单位下用户
     * @param unit
     */
    void deleteAndChild(Sys_unit unit);
}
