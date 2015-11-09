package com.bike.server.user;

import com.bike.entity.User;

import java.util.List;

/**
 * Created by zz on 2015/9/18.
 */
public interface UserService
{
    public User userLogin(String userName, String passWord);

    public void addUser(User user);

    public User userById(String userId);
}
