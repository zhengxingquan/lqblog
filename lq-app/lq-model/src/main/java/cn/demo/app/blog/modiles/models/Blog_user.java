package cn.demo.app.blog.modiles.models;

import cn.demo.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2018/7/7-15:11
 * @desc : 博客的栏目
 **/

@Table("blog_user")
public class Blog_user extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
