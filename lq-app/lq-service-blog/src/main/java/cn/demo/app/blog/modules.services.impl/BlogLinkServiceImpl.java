package cn.demo.app.blog.modules.services.impl;


import cn.demo.app.blog.modiles.models.Blog_link;
import cn.demo.app.blog.modules.services.BlogLinkService;
import cn.demo.framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * @author : zhengxingquan(956607644@qq.com)
 * @time : 2018/7/7-17:49
 * @desc : 博客链接类
 **/

@IocBean(args = {"refer:dao"})
@Service(interfaceClass = BlogLinkService.class)
public class BlogLinkServiceImpl extends BaseServiceImpl<Blog_link> implements BlogLinkService {

    public BlogLinkServiceImpl(Dao dao) {
        super(dao);
    }

}
