package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查询角色列表(条件)
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role) {
        List<Role> allRole = roleService.findAllRole(role);
        return new ResponseResult(true, 200, "查询所有角色成功", allRole);

    }

    /**
     * 添加&修改角色
     *
     * @param role
     */
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role) {
        if (role.getId() == null) {
            roleService.saveRole(role);
            return new ResponseResult(true, 200, "添加角色成功", null);
        } else {
            roleService.updateRole(role);
            return new ResponseResult(true, 200, "修改角色成功", null);

        }

    }

    /**
     * 查询所有的父子菜单信息
     */
    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findMenuByRoleId() {
        List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList", subMenuListByPid);
        return new ResponseResult(true, 200, "查询所有的父子菜单信息成功", map);
    }

    /**
     * 根据角色ID查询菜单信息
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId) {
        List<String> menuByRoleId = roleService.findMenuByRoleId(roleId);
        return new ResponseResult(true, 200, "根据角色ID查询菜单信息成功", menuByRoleId);


    }

    /**
     * 为角色分配菜单列表
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo) {
        roleService.roleContextMenu(roleMenuVo);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", "");
        return result;
    }

    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id) {
        roleService.deleteRole(id);
        return new ResponseResult(true, 200, "删除角色成功", "");
    }

}
