package com.lagou.service;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {
    /**
     * 获取所有广告位
     */
    public List<PromotionSpace> findAllPromotionSpaceMapper();

    void savePromotionSpace(PromotionSpace promotionSpace);

    void updatePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 根据id 查询广告位信息
     * */
    PromotionSpace findPromotionSpaceById(int id);


}
