package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    PromotionAdMapper promotionAdMapper;

    @Override
    public PageInfo findAllAdByPage(PromotionAdVo adVo) {
        PageHelper.startPage(adVo.getCurrentPage(), adVo.getPageSize());
        List<PromotionAd> promotionAds = promotionAdMapper.findAllAdByPage();
        PageInfo<PromotionAd> pageInfo = new PageInfo<>(promotionAds);
        return pageInfo;
    }

    /**
     * 保存广告
     *
     * @param promotionAd
     */
    @Override
    public void savePromotionAd(PromotionAd promotionAd) {
        //1.补全信息
        Date date = new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);

        //2.调用陪mapper
        promotionAdMapper.savePromotionAd(promotionAd);
    }

    /**
     * 修改广告
     *
     * @param promotionAd
     */
    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {
        //1.补全信息
        promotionAd.setUpdateTime(new Date());
    }

    /**
     * 回显章节信息
     * @param id
     * @return
     */
    @Override
    public PromotionAd findPromotionAdById(Integer id) {
        return promotionAdMapper.findPromotionAdById(id);

    }

    @Override
    public void updatePromotionAdStatus(int id, int status) {
        PromotionAd promotionAd = new PromotionAd();
        //1.封装数据
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());

        //2.调用mapper
        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
