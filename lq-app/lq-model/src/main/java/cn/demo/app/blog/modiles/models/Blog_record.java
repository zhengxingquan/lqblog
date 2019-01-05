package cn.demo.app.blog.modiles.models;

import cn.demo.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2018/7/7-15:11
 * @desc : 博客的的更新纪录
 **/

@Table("blog_record")
@Comment("博客的维护更新版本记录日志")
public class Blog_record extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    @Name
    @Comment("编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Column
    @Comment("更新内容")
    @ColDefine(type = ColType.VARCHAR, width = 512)
    private String upContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpContent() {
        return upContent;
    }

    public void setUpContent(String upContent) {
        this.upContent = upContent;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Blog_record{");
        sb.append("id='").append(id).append('\'');
        sb.append(", upContent='").append(upContent).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Blog_record that = (Blog_record) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return upContent != null ? upContent.equals(that.upContent) : that.upContent == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (upContent != null ? upContent.hashCode() : 0);
        return result;
    }
}
