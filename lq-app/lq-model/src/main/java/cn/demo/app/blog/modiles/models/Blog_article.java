package cn.demo.app.blog.modiles.models;

import cn.demo.framework.base.model.BaseModel;
import org.nutz.dao.DB;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2018/7/7-15:11
 * @desc : 博客的文章
 **/

@Table("blog_article")
public class Blog_article extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column
    @Name
    @Comment("编号")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Comment("文章的标题")
    @ColDefine(type = ColType.VARCHAR, width = 255)
    @Column
    private String name;

    @Comment("栏目的ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Column
    private String partId;

    @Comment("排序")
    @Column
    @Prev({
            @SQL(db = DB.MYSQL, value = "SELECT IFNULL(MAX(sort),0)+1 FROM blog_article"),
            @SQL(db = DB.ORACLE, value = "SELECT COALESCE(MAX(sort),0)+1 FROM blog_article")
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
    @Comment("浏览量查看量")
    @ColDefine(type = ColType.INT)
    private Integer num;



    @Column
    @Comment("置顶")
    @Default("0")
    private Boolean top;

    @Column
    @Comment("推荐")
    @Default("0")
    private Boolean recommend;

    @Comment("是否显示 1-显示 0-不显示")
    @Column("isShow")
    @Prev(els = @EL("$me.show()"))
    private Boolean show;

    /**
     * 到时候做定时任务发布
     */
    @Column
    @Comment("发布时间")
    private Long pubTime;

    /**
     * 评论人员
     */
    @Many(field = "artId" )
    private List<Blog_comment> commentUsers;

    /**
     * 栏目
     */
    @One(field = "partId")
    private Blog_part part;

    @One(field = "id")
    private Blog_article_content blogArticleContent;


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

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
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

    public Boolean getTop() {
        return top;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Long getPubTime() {
        return pubTime;
    }

    public void setPubTime(Long pubTime) {
        this.pubTime = pubTime;
    }

    public List<Blog_comment> getCommentUsers() {
        return commentUsers;
    }

    public void setCommentUsers(List<Blog_comment> commentUsers) {
        this.commentUsers = commentUsers;
    }

    public Blog_part getPart() {
        return part;
    }

    public void setPart(Blog_part part) {
        this.part = part;
    }

    public Blog_article_content getBlogArticleContent() {
        return blogArticleContent;
    }

    public void setBlogArticleContent(Blog_article_content blogArticleContent) {
        this.blogArticleContent = blogArticleContent;
    }


}
