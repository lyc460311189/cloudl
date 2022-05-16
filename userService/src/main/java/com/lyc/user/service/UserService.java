package com.lyc.user.service;

import com.lyc.user.model.User;

import java.util.Optional;

public interface UserService {
    User getUserByUserName(String userName);
    Optional<User> findUserByName(String userName);
    void resetPassword(String userName);

}
