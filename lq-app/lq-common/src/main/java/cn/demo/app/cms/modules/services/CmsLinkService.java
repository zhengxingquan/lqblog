package cn.demo.app.cms.modules.services;

import cn.demo.app.cms.modules.models.Cms_link;
import cn.demo.framework.base.service.BaseService;

import java.util.List;

public interface CmsLinkService extends BaseService<Cms_link> {
    List<Cms_link> getLinkList(String code, int size);
    void clearCache();
}
