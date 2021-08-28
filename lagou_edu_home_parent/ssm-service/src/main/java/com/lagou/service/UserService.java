package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;

import java.util.List;

public interface UserService {
    /**
     * 用户分页&多条件组合查询
     */
    public PageInfo<User> findAllUserByPage(UserVo userVo);

    /**
     * 动态用户状态
     */
    public void updateUserStatus(Integer id, String status);

    /**
     * 用户登陆
     */
    public User login(User user) throws Exception;

    /**
     * 根据用户id查询关联的角色信息
     */
    public List<Role> findUserRoleById(Integer id);

    /**
     * 用户关联角色
     */
    void userContextRole(UserVo userVo);

    /*
     * 获取用户权限
     * */
    ResponseResult getUserPermissions(Integer id);
}
