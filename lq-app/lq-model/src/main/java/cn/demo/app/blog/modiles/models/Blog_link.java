package cn.demo.app.blog.modiles.models;

import cn.demo.framework.base.model.BaseModel;
import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2018/7/7-15:11
 * @desc : 博客的友情链接
 **/

@Table("blog_link")
public class Blog_link extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Comment("链接全名称")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    @Column
    private String name;

    @Comment("链接显示名称")
    @ColDefine(type = ColType.VARCHAR, width = 120)
    @Column
    private String sortName;

    @Comment("链接地址(url)")
    @ColDefine(type = ColType.VARCHAR, width = 255, notNull = true)
    @Column
    private String url;

    @Comment("链接的打开方式")
    @ColDefine(type = ColType.VARCHAR, width = 20)
    @Column
    private String open;

    @Comment("排序")
    @Column
    @Prev({
            @SQL(db = DB.MYSQL, value = "SELECT IFNULL(MAX(sort),0)+1 FROM blog_link"),
            @SQL(db = DB.ORACLE, value = "SELECT COALESCE(MAX(sort),0)+1 FROM blog_link")
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

    @Column
    @Comment("浏览量")
    @ColDefine(type = ColType.INT)
    @Default("0")
    private Integer num;

    @Column("isshow")
    @Comment("是否启用")
    @ColDefine(type = ColType.INT)
    @Prev(els = @EL("$me.show()"))
    private boolean show;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
