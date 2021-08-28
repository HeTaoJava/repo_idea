package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;

public interface PromotionAdService {
    /**
     * 分页获取所有的广告列表
     */
    public PageInfo findAllAdByPage(PromotionAdVo adVo);

    /**
     * 保存广告
     * @param promotionAd
     */
    void savePromotionAd(PromotionAd promotionAd);

    /**
     * x修改广告
     * @param promotionAd
     */
    void updatePromotionAd(PromotionAd promotionAd);
    /**
     * 回显广告信息
     */
    PromotionAd findPromotionAdById(Integer id);
    /**
     * 广告动态上下线
     * @param
     */
    void updatePromotionAdStatus(int id,int status);
}
