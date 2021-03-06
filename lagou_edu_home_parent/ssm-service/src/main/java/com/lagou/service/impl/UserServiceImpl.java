package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public PageInfo<User> findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(), userVo.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);
        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);
        return pageInfo;
    }

    /**
     * 动态用户状态
     */
    @Override
    public void updateUserStatus(Integer id, String status) {
        //1.补全账户信息
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdate_time(new Date());
        //2.调用mapper
        userMapper.updateUserStatus(user);
    }

    @Override
    public User login(User user) throws Exception {
        //1.调用mapper
        User user1 = userMapper.login(user);
        if (null != user1 && Md5.verify(user.getPassword(), "lagou", user1.getPassword())) {
            return user1;
        } else {
            return null;

        }
    }

    @Override
    public List<Role> findUserRoleById(Integer id) {
        return userMapper.findUserRoleById(id);
    }

    @Override
    public void userContextRole(UserVo userVo) {
        //1.根据用户id清空中间表
        userMapper.deleteUserContextRole(userVo.getUserId());
        //2.再重新间隙关联关系
        for (Integer roleid : userVo.getRoleIdList()) {
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleid);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");
            userMapper.userContextRole(user_role_relation);

        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer id) {
        List<Role> roleList = userMapper.findUserRoleById(id);
        //2.获取角色id,存到list集合中
        ArrayList<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }
        //3.根据角色id查询父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);
        //4.查封封装父菜单关联的子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByiPd(menu.getId());
            menu.setSubMenuList(subMenu);
        }
        //5.获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);
        //6.封装数据并返回
        Map<String, Object> map = new HashMap<>();
        map.put("menuList", parentMenu); //menuList: 菜单权限数据
        map.put("resourceList", resourceList);//resourceList: 资源权限数据
        return new ResponseResult(true, 200, "响应成功", map);


    }
}
