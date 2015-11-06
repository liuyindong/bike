package com.bike.controller;

import com.bike.entity.AjaxEntity;
import com.bike.entity.User;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by ld on 2015/1/23.
 */
public class ControllerBase
{
    protected HttpServletRequest request;

    protected HttpSession session;


    public AjaxEntity ajaxEntity;

    public ModelAndView modelAndView;

    public void getNewAjax()
    {
        this.ajaxEntity = new AjaxEntity();
    }

    public User getUser()
    {
        return (User) session.getAttribute("user");
    }

    public boolean setUser(User user)
    {
        try
        {
            session.setAttribute("user", user);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

}
