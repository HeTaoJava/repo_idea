package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {
    /**
     * 分页获取所有的广告列表
     */
    public List<PromotionAd> findAllAdByPage();

    /**
     * 保存广告
     *
     * @param promotionAd
     */
    void savePromotionAd(PromotionAd promotionAd);

    /**
     * 回显广告信息
     */
    PromotionAd findPromotionAdById(Integer id);

    /**
     * 修改广告
     *
     * @param promotionAd
     */
    void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 广告动态上下线
     * @param promotionAd
     */
    void updatePromotionAdStatus(PromotionAd promotionAd);
}
