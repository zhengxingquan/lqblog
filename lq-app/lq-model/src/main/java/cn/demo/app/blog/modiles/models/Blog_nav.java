package cn.demo.app.blog.modiles.models;

import cn.demo.framework.base.model.BaseModel;
import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2018/7/7-15:11
 * @desc : 博客的导航(轮播图)表
 **/

@Table("blog_nav")
public class Blog_nav extends BaseModel implements Serializable {

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

    @Comment("公告图片")
    @ColDefine(type = ColType.VARCHAR, width = 500)
    @Column
    private String filePath;

    @Comment("是否显示 1-显示 0 不显示")
    @Column("isShow")
    @Prev(els = @EL("$me.show()"))
    private Boolean show;

    @Comment("排序")
    @Column
    @Prev({
            @SQL(db = DB.MYSQL, value = "SELECT IFNULL(MAX(sort),0)+1 FROM blog_nav"),
            @SQL(db = DB.ORACLE, value = "SELECT COALESCE(MAX(sort),0)+1 FROM blog_nav")
    })
    private Integer sort;


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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
