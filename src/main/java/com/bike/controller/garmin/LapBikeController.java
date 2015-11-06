package com.bike.controller.garmin;

import com.bike.controller.ControllerBase;
import com.bike.controller.util.RestAction;
import com.bike.server.garmin.LapBikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zz on 2015/9/15.
 */
@Controller
@RequestMapping("/laps")
public class LapBikeController extends ControllerBase implements RestAction
{
    @Override
    public void initModel(Model model, HttpServletRequest request, HttpSession session) {
        getNewAjax();
    }

    @Autowired
    private LapBikeService lapBikeService;


    @RequestMapping("/statisList")
    public String statisList(Model model)
    {

        model.addAttribute("lapMesg",lapBikeService.lapBikeStatisByUser(1));
        return "garminbike/laps";
    }
}
