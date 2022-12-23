package com.ymx.fbms.controller;


import com.github.pagehelper.PageInfo;
import com.ymx.fbms.pojo.Result;
import com.ymx.fbms.pojo.Role;
import com.ymx.fbms.pojo.User;
import com.ymx.fbms.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ymx
 * @since 2022-12-09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @Value("${server.port}")
    private String serverPort;

    @Value("${server.address}")
    private String serverAddress;

    /**
     * 展示所有普通用户信息分页
     * @param pageNum
     * @param pageSize
     * @param username
     * @return PageInfo<>()
     */
    @GetMapping("/getUserPage")
    public PageInfo<User> getUserPage(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam String username,
                                      @RequestParam String userRole
                                     ) {
        return userService.getUserPageLike(pageNum, pageSize, username, userRole);
    }

    /**
     * 添加或更新一个普通用户
     * @param user
     * @return Result
     */
    @PostMapping("/addOrUpdateNormalUser")
    public Result addOrUpdateNormalUser(@RequestBody User user) {
        if (user.getUsername().contains("admin")) {
            return Result.error(Result.CODE_400, "用户名非法");
        }
        else {
            if (user.getPassword() == null) {
                user.setPassword("123456");
            }
            if (user.getUserRole() == null) {
                user.setUserRole(Role.ROLE_NORMAL);
            }
            if (user.getUserProfile() == null) {
                user.setUserProfile("http://" + serverAddress + ":" + serverPort + "/profile/default.png");
            }
            return Result.success(Result.CODE_200, userService.addOrUpdateUser(user));
        }
    }

    /**
     * 添加或更新一个admin管理员
     * @param user
     * @return Result
     */
    @PostMapping("/addOrUpdateAdministratorUser")
    public Result addOrUpdateAdministratorUser(@RequestBody User user) {
        if (!user.getUsername().contains("admin") && !user.getUsername().contains("root")) {
            return Result.error(Result.CODE_400, "管理员用户名必须包含关键词!");
        }
        else {
            if (user.getPassword() == null) {
                user.setPassword("admin");
            }
            if (user.getUserRole() == null) {
                user.setUserRole(Role.ROLE_ADMINISTRATOR);
            }
            if (user.getUserProfile() == null) {
                user.setUserProfile("http://" + serverAddress + ":" + serverPort + "/profile/default.png");
            }
            return Result.success(Result.CODE_200, userService.addOrUpdateUser(user));
        }
    }

    /**
     * 通过id删除用户
     * @param user
     * @return Result
     */
    @PostMapping("/deleteUserById")
    public Result deleteUserById(@RequestBody User user) {
        if (user.getUserId() == 1) {
            return Result.error(Result.CODE_500);
        }
        int result = userService.deleteByUserId(user);
        if (result == 1) {
            return Result.success(Result.CODE_200, result);
        } else {
            return Result.error(Result.CODE_500);
        }
    }

    /**
     * 通过id批量删除用户
     * @param ids
     * @return Result
     */
    @PostMapping("/deleteBatchByIds")
    public Result deleteBatchByIds(@RequestBody List<Integer> ids) {
        int result = userService.deleteBatchByIds(ids);
        if (result >= 1) {
            return Result.success(Result.CODE_200, result);
        } else {
            return Result.error(Result.CODE_500);
        }
    }

    /**
     * 获取用户总个数
     * @return Result
     */
    @GetMapping("/getUserNum")
    public Result getUserNum() {
        return Result.success(Result.CODE_200, userService.getUserNum());
    }

    /**
     * 根据身份获得用户
     * @param userRole
     * @return Result
     */
    @GetMapping("/getUserByRole")
    public Result getUserByRole(@RequestParam String userRole) {
        return Result.success(Result.CODE_200, userService.getUserByRole(userRole));
    }

    /**
     * 登录
     * @param user
     * @return Result
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        List<User> userList = userService.loginService(user);
        if (!userList.isEmpty()) {
            return Result.success(Result.CODE_200, userList.get(0));
        } else {
            return Result.error(Result.CODE_400, "账户或密码错误");
        }
    }
}

