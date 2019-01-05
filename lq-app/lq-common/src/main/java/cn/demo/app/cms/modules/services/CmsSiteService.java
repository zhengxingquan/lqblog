package cn.demo.app.cms.modules.services;

import cn.demo.app.cms.modules.models.Cms_site;
import cn.demo.framework.base.service.BaseService;

public interface CmsSiteService extends BaseService<Cms_site> {
    /**
     * 通过编码获取站点信息
     *
     * @param code
     * @return
     */
    Cms_site getSite(String code);

    /**
     * 清空缓存
     */
    void clearCache();
}
