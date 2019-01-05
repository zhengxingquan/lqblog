package cn.demo.app.blog.modules.services.impl;


import cn.demo.app.blog.modiles.models.Blog_nav;
import cn.demo.app.blog.modules.services.BlogNavService;
import cn.demo.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2018/7/7-17:49
 * @desc : 博客文章分类
 **/

@IocBean(args = {"refer:dao"})
@Service(interfaceClass = BlogNavService.class)
public class BlogNavServiceImpl extends BaseServiceImpl<Blog_nav> implements BlogNavService {

    public BlogNavServiceImpl(Dao dao) {
        super(dao);
    }

}
