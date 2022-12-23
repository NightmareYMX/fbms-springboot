package com.ymx.fbms.service;

import com.github.pagehelper.PageInfo;
import com.ymx.fbms.pojo.User;
import com.ymx.fbms.pojo.UserEcharts;

import java.util.List;

/**
 * @author ymx
 * @since 2022-12-09
 */
public interface UserService {
    /**
     * 分页查询用户
     * @param pageNum
     * @param pageSize
     * @param username
     * @return
     */
    PageInfo<User> getUserPageLike(Integer pageNum, Integer pageSize, String username, String userRole);

    /**
     * 得到所有NROMAL用户的数据
     * @return
     */
    List<UserEcharts> getUserAddressCount();

    /**
     * 获得用户总个数
     * @return
     */
    int getUserNum();

    /**
     * 增添新用户
     * @param user
     * @return
     */
    int addOrUpdateUser(User user);

    /**
     * 通过id删除用户
     * @param user
     * @return
     */
    int deleteByUserId(User user);

    /**
     * 批量删除用户
     * @param ids
     * @return int
     */
    int deleteBatchByIds(List<Integer> ids);

    /**
     * 登录
     * @param user
     * @return
     */
    List<User> loginService(User user);

    /**
     * 获得所有admin用户信息
     * @return
     */
    List<User> getUserByRole(String userRole);
}
