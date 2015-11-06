package com.bike.controller.user;

import com.bike.controller.ControllerBase;
import com.bike.controller.util.RestAction;
import com.bike.entity.User;
import com.bike.server.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by zz on 2015/9/18.
 */
@Controller
@RequestMapping("/user")
public class UserController  extends ControllerBase implements RestAction
{
    @Override
    public void initModel(Model model, HttpServletRequest request, HttpSession session) {
        this.session = session;
    }

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Object userLogin(String userName,String pwd)
    {
       User user = userService.userLogin(userName, pwd);

        if(user != null)
        {
            setUser(user);
        }

        return user;
    }

    @RequestMapping("/add")
    public void add()
    {
        User user = new User();

        user.setNickName("系统管理员");
        user.setId(UUID.randomUUID().toString());
        user.setPassWord("123");
        user.setUserName("admin");

        userService.addUser(user);
    }

}
