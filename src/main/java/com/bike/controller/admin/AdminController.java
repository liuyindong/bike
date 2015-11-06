package com.bike.controller.admin;

import com.bike.controller.ControllerBase;
import com.bike.entity.BikeMessages;
import com.bike.entity.BikeTag;
import com.bike.server.NewsType.NewsTypeServer;
import com.bike.server.message.BikeMessageServer;
import com.bike.server.tags.TagServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * user： ld
 * date： 2015/1/30
 * time:  13:24
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends ControllerBase
{
    @Autowired
    private NewsTypeServer newsTypeServer;

    @Autowired
    private BikeMessageServer bikeMessageServer;

    @Autowired
    private TagServer tagServer;



    @RequestMapping
    public ModelAndView index()
    {
        modelAndView = new ModelAndView("admin/index");
        return modelAndView;
    }

    @RequestMapping("/to/newsView")
    public ModelAndView toNewsView()
    {
        modelAndView = new ModelAndView("admin/news/newsView");


        BikeMessages bikeMessages = new BikeMessages();
        bikeMessages.setStatusId(1);

        modelAndView.addObject("bikeMessages",bikeMessageServer.bikeMessageList(bikeMessages));
        return modelAndView;

    }

    @RequestMapping("/to/addNews")
    public ModelAndView toAddNew()
    {
        modelAndView = new ModelAndView("admin/news/addNews");
        modelAndView.addObject("newTypeList", newsTypeServer.newsTypeList());

        return modelAndView;
    }

    @RequestMapping("/syncNews")
    public void syncNews()
    {
        List<BikeMessages> messagesList = bikeMessageServer.bikeMessageList(new BikeMessages());

        for (BikeMessages bikeMessages : messagesList)
        {
            List<BikeTag> bikeTagList = tagServer.tagByNewUUID(bikeMessages.getUuid());

            System.out.println(bikeTagList.size());

            bikeMessages.setBikeTagList(bikeTagList);

            bikeMessageServer.add(bikeMessages);

            System.out.print(bikeMessages.getTitle());
        }
    }
}
