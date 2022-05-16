package com.lyc.user.service.impl;

import com.lyc.user.model.User;
import com.lyc.user.service.UserService;

import java.util.Optional;

/**
 * @author liyuchun
 * @version 1.0
 * @description: TODO
 * @date 2022/5/16 15:35
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUserByUserName(String userName) {
        User user = new User();
        user.setId(1l);
        user.setUserName("张三");
        return user;
    }

    @Override
    public Optional<User> findUserByName(String userName) {
        return Optional.empty();
    }

    @Override
    public void resetPassword(String userName) {
        System.out.println("重置密码成功！");
    }
}
