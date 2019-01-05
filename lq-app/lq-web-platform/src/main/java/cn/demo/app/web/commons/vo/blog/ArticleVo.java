package cn.demo.app.web.commons.vo.blog;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2018/7/22-12:14
 * @desc : 输入描述
 **/
public class ArticleVo {
    private String name;
    private String beginDate;
    private String endDate;
    private String type;
    private Boolean show;
    private String partId;
    private Boolean top;
    private Boolean recommend;

    public ArticleVo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        if ("top".equals(type)) {
            this.top = true;
        } else if ("noTop".equals(type)) {
            this.top = false;
        } else if ("recommend".equals(type)) {
            this.recommend = true;
        } else if ("noRecommend".equals(type)) {
            this.recommend = false;
        }
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
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
}
