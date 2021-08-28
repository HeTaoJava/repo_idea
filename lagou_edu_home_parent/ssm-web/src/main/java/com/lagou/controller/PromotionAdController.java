package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import com.lagou.utils.CompressPic;
import org.apache.ibatis.mapping.ResultMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {
    @Autowired
    PromotionAdService promotionAdService;

    /**
     * 分页查询所有广告信息
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVo promotionAdVo) {
        PageInfo allAdByPage = promotionAdService.findAllAdByPage(promotionAdVo);
        return new ResponseResult(true, 200, "响应成功", allAdByPage);

    }

    /**
     * 课程图片上传
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpLoad(MultipartFile file, HttpServletRequest request) throws IOException {

        Map map = CompressPic.addImages(file, request);

        return new ResponseResult(true, 200, "响应成功", map);
    }

    /**
     *添加或者修改广告信息
     * @param promotionAd
     * @return
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {
        if (promotionAd.getId() == null) {//保存广告
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true, 200, "广告添加成功", null);
        }else{//修改广告
            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true, 200, "广告修改成功", null);
        }
    }
    /**
     * 回显广告信息
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(Integer id){
        PromotionAd promotionAdById = promotionAdService.findPromotionAdById(id);
        return new ResponseResult(true, 200, "广告修改成功", promotionAdById);


    }
    /**
     * 广告动态上下线
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id, Integer status){
        promotionAdService.updatePromotionAdStatus(id,status);
        Map<String,Integer> map = new HashMap();
        map.put("status",status);
        return new ResponseResult(true, 200, "广告动态上下线成功", map);
    }
}
