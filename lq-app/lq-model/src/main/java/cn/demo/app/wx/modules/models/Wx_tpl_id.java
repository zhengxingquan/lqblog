package cn.demo.app.wx.modules.models;

import cn.demo.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * Created by demo on 2016/8/5.
 */
@Table("wx_tpl_id")
@TableIndexes({@Index(name = "INDEX_WX_TPL_ID", fields = {"id", "wxid"}, unique = true)})
public class Wx_tpl_id extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column
    @Comment("模板编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String id;

    @Column
    @Comment("单位id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String sys_unit_id;

    @Column
    @Comment("模板ID")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    private String template_id;

    @Column
    @Comment("微信ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String wxid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    public String getSys_unit_id() {
        return sys_unit_id;
    }

    public void setSys_unit_id(String sys_unit_id) {
        this.sys_unit_id = sys_unit_id;
    }
}
