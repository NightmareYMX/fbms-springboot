package com.ymx.fbms.mapper;

import com.ymx.fbms.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ymx
 * @since 2022-12-09
 */
public interface UserMapper {
    /**
     * 获取所有用户信息(模糊查询)
     * @return List<User>
     */
    List<User> getUserByUsernameLike(@Param("username") String username, @Param("userRole") String userRole);

    /**
     * 获得所有用户地址的类别
     * @return List<String>
     */
    List<String> getAllUserAddress();

    /**
     * 获得用户地址每个类别的个数
     * @param userAddress
     * @return int
     */
    int getUserAddressCount(@Param("userAddress") String userAddress);

    /**
     * 通过id查询用户
     * @param userId
     * @return User
     */
    User getUserById(@Param("userId") Integer userId);
    /**
     * 获得用户个数
     * @return int
     */
    int getUserNum();

    /**
     * 添加用户
     * @param user
     * @return int
     */
    int addUser(@Param("user") User user);

    /**
     * 更新用户
     * @param user
     * @return int
     */
    int updateUser(@Param("user") User user);

    /**
     * 通过用户id删除用户
     * @param user
     * @return int
     */
    int deleteById(@Param("user") User user);

    /**
     * 批量删除用户通过id
     * @param ids
     * @return int
     */
    int deleteBatchByIds(@Param("ids") List<Integer> ids);

    /**
     * 通过username和password查询用户
     * @param user
     * @return User
     */
    List<User> selectUserByUsernameAndPassword(@Param("user") User user);
}
