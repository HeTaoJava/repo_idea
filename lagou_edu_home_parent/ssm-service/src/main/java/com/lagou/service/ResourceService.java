package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

public interface ResourceService {
    /**
     * 资源分页及多条件查询
     * @return
     */
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
