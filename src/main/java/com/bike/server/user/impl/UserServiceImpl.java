package com.bike.server.user.impl;

import com.bike.entity.User;
import com.bike.server.ServerBase;
import com.bike.server.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by zz on 2015/9/18.
 */
@Service
public class UserServiceImpl extends ServerBase implements UserService {
    @Override
    public User userLogin(String userName, String passWord)
    {
        return mongoTemplate.findOne(query(where("userName").is(userName).and("passWord").is(passWord)),User.class);
    }

    @Override
    public void addUser(User user) {
        mongoTemplate.save(user);
    }
}
