package cn.demo.app.blog.modiles.models;

import cn.demo.framework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2018/7/7-15:11
 * @desc : 博客的文章的评论人员
 **/

@Table("blog_comment")
public class Blog_comment extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    @Comment("文章的ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Column
    private String artId;


    @Comment("用户的ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Column
    private String userId;

    @Comment("评论内容")
    @ColDefine(type = ColType.VARCHAR, width = 500)
    @Column
    private String content;

    @Comment("评论内容的父节点")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Column
    private String parentId;

    @Comment("点赞数量")
    @Column
    @Prev({
//            @SQL(db = DB.MYSQL, value = "SELECT IFNULL(supportNum,0)+1 FROM blog_art_com WHERE artId = @id"),
//            @SQL(db = DB.ORACLE, value = "SELECT COALESCE(supportNum,0)+1 FROM blog_art_com WHERE artId = @id")
    })
    private Integer supportNum;

    @Comment("反对数量")
    @Column
    @Prev({
//            @SQL(db = DB.MYSQL, value = "SELECT IFNULL(opposeNum,0)+1 FROM blog_art_com WHERE artId = @id"),
//            @SQL(db = DB.ORACLE, value = "SELECT COALESCE(opposeNum,0)+1 FROM blog_art_com WHERE artId = @id")
    })
    private Integer opposeNum;

    @One(field = "userId")
    private Blog_user users;

    @Many(field = "parentId")
    private List<Blog_comment> commentUsers;

    @One(field = "parentId")
    private Blog_comment parentComt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Blog_user getUsers() {
        return users;
    }

    public void setUsers(Blog_user users) {
        this.users = users;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<Blog_comment> getCommentUsers() {
        return commentUsers;
    }

    public void setCommentUsers(List<Blog_comment> commentUsers) {
        this.commentUsers = commentUsers;
    }

    public Blog_comment getParentComt() {
        return parentComt;
    }

    public void setParentComt(Blog_comment parentComt) {
        this.parentComt = parentComt;
    }

    public Integer getSupportNum() {
        return supportNum;
    }

    public void setSupportNum(Integer supportNum) {
        this.supportNum = supportNum;
    }

    public Integer getOpposeNum() {
        return opposeNum;
    }

    public void setOpposeNum(Integer opposeNum) {
        this.opposeNum = opposeNum;
    }
}
