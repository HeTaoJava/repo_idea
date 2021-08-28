package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {
    /**
     * 用户分页&多条件组合查询
     */
    public List<User> findAllUserByPage(UserVo userVo);

    /**
     * 动态用户状态
     */
    public void updateUserStatus(User user);

    /**
     * 用户登陆
     */
    public User login(User user);

    /**
     * 根据用户id清空中间表
     */
    void deleteUserContextRole(Integer userId);

    /**
     * 分配角色
     */
    void userContextRole(User_Role_relation user_role_relation);

    /**
     * 根据用户id查询关联的角色信息
     */
    public List<Role> findUserRoleById(Integer id);
    /**
     * 根据角色id,查询角色所拥有的顶级菜单
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);
    /**
     * 根据pid,查询子菜单信息
     */
    public List<Menu> findSubMenuByiPd(Integer pid);
    /**
     * 获取用户拥有的权限信息
     */
    public List<Resource> findResourceByRoleId(List<Integer> roleId);

}
