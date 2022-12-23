package com.ymx.fbms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymx.fbms.pojo.User;
import com.ymx.fbms.mapper.UserMapper;
import com.ymx.fbms.pojo.UserEcharts;
import com.ymx.fbms.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ymx
 * @since 2022-12-09
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${files.upload.profile}")
    private String profilePath;

    @Resource
    UserMapper userMapper;

    @Override
    public PageInfo<User> getUserPageLike(Integer pageNum, Integer pageSize, String username, String userRole) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(userMapper.getUserByUsernameLike(username, userRole));
    }

    @Override
    public List<UserEcharts> getUserAddressCount() {
        List<String> allUserAddress = userMapper.getAllUserAddress();
        List<UserEcharts> userEcharts = new ArrayList<>();
        for (String userAddress:
             allUserAddress) {
            String[] nameSplit = userAddress.split("省");
            int userAddressCount = userMapper.getUserAddressCount(userAddress);
            userEcharts.add(new UserEcharts(nameSplit[1], userAddressCount));
        }
        return userEcharts;
    }

    @Override
    public int getUserNum() {
        return userMapper.getUserNum();
    }

    @Override
    public int addOrUpdateUser(User user){
        if (user.getUserId() != null) {
            String userProfile = userMapper.getUserById(user.getUserId()).getUserProfile();
            try {
                if (!userProfile.equals(user.getUserProfile())) {
                    String path = userProfile.split("/")[4];
                    if (!path.equals("default.png")) {
                        Files.delete(Paths.get(profilePath + File.separator + path));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
            return userMapper.updateUser(user);
        } else {
            return userMapper.addUser(user);
        }
    }

    @Override
    public int deleteByUserId(User user) {
        return userMapper.deleteById(user);
    }

    @Override
    public int deleteBatchByIds(List<Integer> ids) {
        return userMapper.deleteBatchByIds(ids);
    }

    @Override
    public List<User> loginService(User user) {
        return userMapper.selectUserByUsernameAndPassword(user);
    }

    @Override
    public List<User> getUserByRole(String userRole) {
        return userMapper.getUserByUsernameLike("", userRole);
    }
}
