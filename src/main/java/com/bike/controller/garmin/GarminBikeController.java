package com.bike.controller.garmin;

import com.alibaba.fastjson.JSON;
import com.bike.controller.ControllerBase;
import com.bike.controller.util.RestAction;
import com.bike.entity.Page;
import com.bike.entity.User;
import com.bike.entity.garmin.*;
import com.bike.server.garmin.GarminBikeService;
import com.bike.server.garmin.GarminService;
import com.bike.server.garmin.LapBikeService;
import com.bike.server.garmin.impl.GarminServiceImpl;
import com.bike.server.util.ServiceUtil;
import com.bike.util.Config;
import com.bike.util.DateUtil;
import com.bike.util.MyConstants;
import com.garmin.fit.DateTime;
import com.garmin.fit.LapMesg;
import com.garmin.fit.RecordMesg;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zz on 2015/9/2.
 */
@Controller
@RequestMapping("/garmin")
public class GarminBikeController extends ControllerBase implements RestAction {
    @Autowired
    private GarminService garminService;

    @Autowired
    private GarminBikeService garminBikeService;

    @Autowired
    private LapBikeService lapBikeService;

    @Autowired
    private ServiceUtil serviceUtil;

    private String dateType = "yyyy-MM-dd-HH-mm";


    @Override
    public void initModel(Model model, HttpServletRequest request, HttpSession session)
    {
        this.request = request;
        this.session = session;
        getNewAjax();
    }


    @RequestMapping("/userBike")
    @ResponseBody
    public Object userBikeById(Integer pageNo)
    {
        PageRequest pageRequest = new PageRequest(pageNo,5);
        return garminBikeService.userBikeById(getUser().getId(),pageRequest);
    }


    @RequestMapping("/toupload")
    public String toUploadFit() {
        return "garminbike/upload";
    }

    @Autowired
    private Config config;

    @RequestMapping("/upload")
    @ResponseBody
    public Object fitUpload(@RequestParam MultipartFile file,HttpSession session,HttpServletResponse response) {
        try
        {

            ajaxEntity.setErrors("上传失败");


            UserBikeFitSession userBikeFitSession = garminService.getBikeFitSession(file.getInputStream());
            userBikeFitSession.setUserId(getUser().getId());

            String name = getUser().getUserName() + "/" + DateUtil.addressRadom(userBikeFitSession.getStartTime()) ;

            name += DateUtil.timeToString(userBikeFitSession.getStartTime(),dateType) + DateUtil.imgType(file.getOriginalFilename());

            String path = config.getUploadFilePath() + "/uploadNow/" +name;

            userBikeFitSession.setFitPath(name);



            //保存上传文件
            int result = serviceUtil.uploadFile(file,path);

            garminBikeService.addGarminBikeFitSession(userBikeFitSession);


           /* switch (result)
            {
                case -1:

                    response.setStatus(500);
                    ajaxEntity.setErrors("已经上传过了");
                    break;
                case 0:
                    response.setStatus(500);
                    break;
                case 1:
                    //添加用户记录
                    garminBikeService.addGarminBikeFitSession(userBikeFitSession);
                    break;
            }
*/
            ajaxEntity.setSuccess(true);


        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);

        }
        return ajaxEntity;
    }

    @RequestMapping(value = "/addBikeGroup", method = RequestMethod.POST)
    @ResponseBody
    public Object updateBikeGroup(String[] uploadName,String groupType,String groupNum,String remarks)
    {
        garminBikeService.updateBikeGroup(uploadName,groupType,groupNum,remarks);
        ajaxEntity.setSuccess(true);
        return ajaxEntity;
    }



    @RequestMapping("/getFitMsg")
    public String getFitMsg(String name, Model model,LapBikeParam lapBikeParam)
    {
        try {

            FileInputStream in = new FileInputStream(config.getUploadFilePath() + name);

            UserBikeFitSession userBikeFitSession = garminService.getBikeFitSession(in);

            lapBikeParam.setUserBikeFitSession(userBikeFitSession);

            model.addAttribute("lapMesg", lapBikeService.getLapBike(lapBikeParam));

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return "garminbike/laps";

    }

    @RequestMapping("/toSamples")
    public String toSamples() {
        return "garminbike/samples";
    }


   /* @RequestMapping("/samples/data")
    @ResponseBody
    public Object samplesData(String name,Float time, Model model) {
        FileInputStream in = null;
        try {

            in = new FileInputStream(config.getUploadFilePath() + name);
            UserBikeFitSession userBikeFitSession = garminService.getBikeFitSession(in);

            for (LapBike lapMesg : userBikeFitSession.getLapBikeList())
            {

                if (time == null || lapMesg.getTotalTimerTime().compareTo(time) == 0)
                {


                    int startIndex = 0;
                    int endIndex = userBikeFitSession.getGarminBike().getTimestamp().size();


                   *//* for (int i = 0; i < garminBike.getTimestamp().size(); i++) {
                        DateTime recordTime = garminBike.getTimestamp().get(i);
                        if (recordTime.equals(lapMesg.getStartTime())) {
                            startIndex = i;
                        } else if (recordTime.equals(lapMesg.getEndTime())) {
                            endIndex = i;
                            break;
                        }
                    }*//*


                    BikeChart bikeChart = new BikeChart();

                    for (Float distance : userBikeFitSession.getGarminBike().getDistance().subList(startIndex,endIndex)) {
                        bikeChart.addXData(distance / 1000);
                    }

                    BikeDetails bikeSpeed = new BikeDetails();
                    bikeSpeed.setName("速度");
                    bikeSpeed.setType("line");
                    bikeSpeed.setUnit("km/h");
                    bikeSpeed.setValueDecimals(1);
                    for (Float speed : userBikeFitSession.getGarminBike().getSpeed().subList(startIndex, endIndex)) {
                        bikeSpeed.addData(speed * 3.6);
                    }
                    bikeChart.addDatasets(bikeSpeed);

                    BikeDetails bikeHeartRate = new BikeDetails();
                    bikeHeartRate.setName("心率");
                    bikeHeartRate.setType("line");
                    bikeHeartRate.setUnit("bpm");
                    bikeHeartRate.setValueDecimals(0);

                    for (Short heartRate : userBikeFitSession.getGarminBike().getHeartRate().subList(startIndex, endIndex)) {
                        bikeHeartRate.addData(heartRate);
                    }
                    bikeChart.addDatasets(bikeHeartRate);


                    BikeDetails bikePower = new BikeDetails();
                    bikePower.setName("功率");
                    bikePower.setType("line");
                    bikePower.setUnit("watts");
                    bikePower.setValueDecimals(0);

                    for (Integer power : userBikeFitSession.getGarminBike().getPower().subList(startIndex, endIndex)) {
                        bikePower.addData(power);
                    }
                    bikeChart.addDatasets(bikePower);

                    BikeDetails bikeCadence = new BikeDetails();
                    bikeCadence.setName("踏频");
                    bikeCadence.setType("line");
                    bikeCadence.setUnit("watts");
                    bikeCadence.setValueDecimals(0);

                    for (Short cadence : userBikeFitSession.getGarminBike().getCadence().subList(startIndex, endIndex)) {
                        bikeCadence.addData(cadence);
                    }
                    bikeChart.addDatasets(bikeCadence);

                    return bikeChart;
                }


            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {

        List<Point> pointList = new ArrayList<>();

        for (int i = 1;i <= 10;i++)
        {
            Point point = new Point(i,i+100);

            System.out.println(point.toString());

            pointList.add(point);
        }



    }
*/

}
