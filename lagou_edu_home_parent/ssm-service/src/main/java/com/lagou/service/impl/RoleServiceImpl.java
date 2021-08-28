package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        return roleMapper.findAllRole(role);
    }

    /**
     * 添加角色
     *
     * @param role
     */
    @Override
    public void saveRole(Role role) {
        //1.补全信息
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("System");
        role.setUpdatedBy("System");
        //2.调用mapper
        roleMapper.saveRole(role);
    }

    /**
     * 回显角色信息
     */

    /**
     * 修改角色
     *
     * @param role
     */
    @Override
    public void updateRole(Role role) {
        //1.补全信息
        Date date = new Date();

        role.setUpdatedTime(date);
        role.setCreatedTime(date);
        role.setCreatedBy("System");
        role.setUpdatedBy("System");
        //2.调用mapper
        roleMapper.updateRole(role);

    }

    @Override
    public List<String> findMenuByRoleId(Integer roleId) {
        return roleMapper.findMenuByRoleId(roleId);
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        //1.清空中间表关联关系
        roleMapper.deleteContextMenu(roleMenuVo.getRoleId());
        //2.为角色分配菜单信息
        List<Integer> menuIdList = roleMenuVo.getMenuIdList();
        for (Integer mid : menuIdList) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            //2.封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");
            roleMapper.roleContextMenu(role_menu_relation);

        }
    }

    @Override
    public void deleteRole(Integer id) {
        //1.清空中间表
        roleMapper.deleteContextMenu(id);
        //2.删除角色
        roleMapper.deleteRole(id)   ;
    }
}
