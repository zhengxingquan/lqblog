package cn.demo.app.blog.modiles.models;

import cn.demo.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2018/7/7-15:11
 * @desc : 博客的文章
 **/

@Table("blog_article_content")
public class Blog_article_content extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    @Name
    @Comment("文章的编号对应于 blog_article 表中的ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String id;

    @Comment("文章内容")
    @ColDefine(type = ColType.TEXT)
    @Column
    private String content;


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


    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Blog_article_content that = (Blog_article_content) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return content != null ? content.equals(that.content) : that.content == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Blog_article_content{");
        sb.append("id='").append(id).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
