package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {
    /**
     * 查询角色列表(条件)
     */
    public List<Role> findAllRole(Role role);

    /**
     * 添加角色
     *
     * @param role
     */
    void saveRole(Role role);
    /**
     * 回显角色信息
     */
    /**
     * 修改角色
     *
     * @param role
     */
    void updateRole(Role role);

    /**
     * 根据角色ID查询菜单信息
     */
    List<String> findMenuByRoleId(Integer roleId);

    /**
     * 根據roleId清空中间表的关系
     */
    public void deleteContextMenu(Integer rid);

    /**
     * 为角色分配菜单列表
     */
    public void roleContextMenu(Role_menu_relation role_menu_relation);

    /**
     * 删除角色
     */
    void deleteRole(Integer id);


}
