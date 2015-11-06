package com.bike.controller.garmin;

import com.bike.controller.ControllerBase;
import com.bike.entity.garmin.GarminBike;
import com.bike.server.garmin.GarminService;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.series.Line;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by zz on 2015/8/27.
 */
@Controller
@RequestMapping("/fit")
public class GarminController extends ControllerBase
{
    @Autowired
    private GarminService garminService;


    @RequestMapping
    public String toGarminAdmin()
    {
        return "/admin/garmin/index";
    }


    @RequestMapping("/upload")
    @ResponseBody
    public Object getFitMsg(@RequestParam(value = "file", required = false) MultipartFile file)
    {
        try {
           // FileInputStream in = new FileInputStream();

            garminService.getBikeFitSession(file.getInputStream());

            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error";

    }

    @RequestMapping("/toGrmin")
    public String toGarmin()
    {
        return "garmin/index";
    }


    @RequestMapping("/getFitMsg")
    @ResponseBody
    public Object fitMsg(String fileName)
    {

        try
        {
            /*FileInputStream in = new FileInputStream(fileName);
            GarminBike graminBike = garminService.getBikeFitSession(in);

            EnhancedOption option = new EnhancedOption();
            option.tooltip().trigger(Trigger.axis);
            option.calculable(true);
            option.xAxis(new CategoryAxis().boundaryGap(false).data(graminBike.getDistance().toArray()));
            CategoryAxis categoryAxis = new CategoryAxis();
            categoryAxis.type(AxisType.value);
            categoryAxis.axisLabel().formatter("{value} bmp");
            option.yAxis(categoryAxis);
            option.series(new Line().name("xin lv").data(graminBike.getHeartRate().toArray()));*/





           // return option.toString();

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

}
