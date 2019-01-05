package cn.demo.app.blog.modules.services.impl;


import cn.demo.app.blog.modiles.models.Blog_part;
import cn.demo.app.blog.modules.services.BlogCategoryService;
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
@Service(interfaceClass = BlogCategoryService.class)
public class BlogCategoryServiceImpl extends BaseServiceImpl<Blog_part> implements BlogCategoryService {

    public BlogCategoryServiceImpl(Dao dao) {
        super(dao);
    }

}
