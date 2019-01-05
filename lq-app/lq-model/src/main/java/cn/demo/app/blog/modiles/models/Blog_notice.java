package cn.demo.app.blog.modiles.models;

import cn.demo.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2018/7/7-15:11
 * @desc : 博客的公告表
 **/

@Table("blog_notice")
public class Blog_notice extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Comment("公告内容")
    @ColDefine(type = ColType.VARCHAR, width = 500)
    @Column
    private String content;

    @Comment("是否显示 1-显示 0 不显示")
    @Column("isShow")
    @Prev(els = @EL("$me.show()"))
    private Boolean show;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }
}
