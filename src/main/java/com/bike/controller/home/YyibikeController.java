package com.bike.controller.home;

import com.bike.controller.ControllerBase;
import com.bike.server.message.BikeMessageServer;
import com.bike.server.tags.TagServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2015/1/24.
 * 网站首页
 */
@Controller
@RequestMapping("/home/*")
public class YyibikeController extends ControllerBase
{
    @Autowired
    private BikeMessageServer bikeMessageServer;

    @Autowired
    private TagServer tagServer;

    @RequestMapping("/")
    public ModelAndView bikeHome()
    {
        ModelAndView mav = new ModelAndView("home/index");
        mav.addObject("page",bikeMessageServer.getPages(1));



        return mav;
    }
}
