package cn.demo.app.sys.modules.models;

import cn.demo.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by demo on 2016/6/21.
 */
@Table("sys_config")
public class Sys_config extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Name
    @Column
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String configKey;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String configValue;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String note;

    public String getConfigKey() {
        return configKey;
    }

    public Sys_config setConfigKey(String configKey) {
        this.configKey = configKey;
        return  this;
    }

    public String getConfigValue() {
        return configValue;
    }

    public Sys_config setConfigValue(String configValue) {
        this.configValue = configValue;
        return  this;
    }

    public String getNote() {
        return note;
    }

    public Sys_config setNote(String note) {
        this.note = note;
        return  this;
    }
}
