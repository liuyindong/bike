package com.bike.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ld on 2015/1/27.
 */
@Controller
@RequestMapping("/error/*")
public class ErrorControll
{


    @RequestMapping("/404")
    public String fourOrFour(HttpServletRequest request)
    {
        return "system/404";
    }

    @RequestMapping("/exception")
    public String exception(HttpServletRequest request)
    {
        return "system/404";
    }


}