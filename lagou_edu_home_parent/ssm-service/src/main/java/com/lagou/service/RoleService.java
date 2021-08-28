package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {
    /**
     查询角色列表(条件)
     */
    public List<Role> findAllRole(Role role);

    void saveRole(Role role);

    void updateRole(Role role);

    /**
     * 根据角色ID查询菜单信息
     */
    List<String> findMenuByRoleId(Integer roleId);

    /**
     * 为角色分配菜单
     */
    public void roleContextMenu(RoleMenuVo roleMenuVo);
    /**
     * 删除角色
     */
    void deleteRole(Integer id);
}
