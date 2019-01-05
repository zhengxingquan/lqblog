package cn.demo.app.web.commons.vo.blog;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2018/7/22-18:47
 * @desc : 输入描述
 **/
public class TreeVo {
      private String id;
      private String text;
      private Boolean children;

    public TreeVo() {
    }

    public TreeVo(String id, String text, Boolean children) {
        this.id = id;
        this.text = text;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getChildren() {
        return children;
    }

    public void setChildren(Boolean children) {
        this.children = children;
    }
}
