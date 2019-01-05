package cn.demo.app.blog.modiles.models;

import cn.demo.framework.base.model.BaseModel;
import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2018/7/7-15:11
 * @desc : 博客的栏目
 **/

@Table("blog_part")
public class Blog_part extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Comment("栏目全名称")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    @Column
    private String name;

    @Comment("栏目显示名称")
    @ColDefine(type = ColType.VARCHAR, width = 120)
    @Column
    private String sortName;

    @Comment("排序")
    @Column
    @Prev({
            @SQL(db = DB.MYSQL, value = "SELECT IFNULL(MAX(sort),0)+1 FROM blog_part"),
            @SQL(db = DB.ORACLE, value = "SELECT COALESCE(MAX(sort),0)+1 FROM blog_part")
    })
    private Integer sort;

    @Comment("简介")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    @Column
    private String description;


    @Comment("图标")
    @ColDefine(type = ColType.VARCHAR, width = 50)
    @Column
    private String icon;

    @Comment("是否显示")
    @Column("isshow")
    @Prev(els = @EL("$me.show()"))
    private boolean show;

    @Comment("是否有孩子节点")
    @Column("hasChildren")
    @Default("0")
    private boolean hasChildren;

    @Many(field = "partId")
    private List<Blog_article> articles;

    @Comment("父栏目")
    @Column("pid")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String parentId;

    @Many(field = "parentId")
    private List<Blog_part> blogParts;

    @One(field = "parentId")
    private Blog_part parentPart;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Blog_article> getArticles() {
        return articles;
    }

    public void setArticles(List<Blog_article> articles) {
        this.articles = articles;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<Blog_part> getBlogParts() {
        return blogParts;
    }

    public void setBlogParts(List<Blog_part> blogParts) {
        this.blogParts = blogParts;
    }

    public Blog_part getParentPart() {
        return parentPart;
    }

    public void setParentPart(Blog_part parentPart) {
        this.parentPart = parentPart;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }


}
