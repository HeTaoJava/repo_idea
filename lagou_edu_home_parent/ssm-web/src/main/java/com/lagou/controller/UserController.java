package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 用户分页&多条件组合查询
     */
    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo) {
        PageInfo<User> allUserByPage = userService.findAllUserByPage(userVo);
        return new ResponseResult(true, 200, "分页多条件查询成功", allUserByPage);

    }

    /**
     * 动态用户状态
     */
    @RequestMapping("/updateUserStatus")
    private ResponseResult updateUserStatus(@RequestParam Integer id, @RequestParam String status) {
        System.out.println("=======================" + status);
        userService.updateUserStatus(id, status);
        return new ResponseResult(true, 200, "动态用户状态成功", status);
    }

    /**
     * 用户登陆
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User user1 = userService.login(user);
        if (null != user1) {
            HttpSession session = request.getSession();
            //保存用户id和access_token到session中
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token", access_token);
            session.setAttribute("user_id", user1.getId());
            //将查询出来的信息响应给前台
            HashMap<String, Object> map = new HashMap<>();
            map.put("access_token", access_token);
            map.put("user_id", user1.getId());
            //将user
            return new ResponseResult(true, 1, "登陆成功", map);

        } else {
            return new ResponseResult(true, 400, "用户名或密码错误", null);
        }
    }

    /**
     * 根据用户id查询关联的角色信息
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id) {
        List<Role> userRoleById = userService.findUserRoleById(id);
        return new ResponseResult(true, 400, "根据用户id查询关联的角色信息成功", userRoleById);
    }
    /**
     分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.userContextRole(userVo);
        return new ResponseResult(true, 400, "分配角色成功", null);
    }
    /**
     * 获取用户权限
     * */
    @RequestMapping("/getUserPermissions")
    private ResponseResult getUserPermissions(HttpServletRequest request){
        //1.获取请求头中的token
        String authorization = request.getHeader("Authorization");
        //2.获取session中的token
        String access_token = (String) request.getSession().getAttribute("access_token");
        //3.判断token是否一致
        if(authorization.equals(access_token)){
            //获取用户id
            Integer userId = (Integer) request.getSession().getAttribute("user_id");
            return userService.getUserPermissions(userId);
        }else {
            return new ResponseResult(false, 400, "获取菜单信息失败", null);
        }


    }
}
