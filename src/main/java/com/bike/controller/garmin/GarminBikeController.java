package com.bike.controller.garmin;

import com.alibaba.fastjson.JSON;
import com.bike.controller.ControllerBase;
import com.bike.controller.util.RestAction;
import com.bike.entity.Page;
import com.bike.entity.User;
import com.bike.entity.baidu.GeoconvResult;
import com.bike.entity.baidu.PointXY;
import com.bike.entity.garmin.*;
import com.bike.server.garmin.GarminBikeService;
import com.bike.server.garmin.GarminService;
import com.bike.server.garmin.LapBikeService;
import com.bike.server.garmin.impl.GarminServiceImpl;
import com.bike.server.user.UserService;
import com.bike.server.util.ServiceUtil;
import com.bike.util.*;
import com.garmin.fit.DateTime;
import com.garmin.fit.LapMesg;
import com.garmin.fit.RecordMesg;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zz on 2015/9/2.
 */
@RestController
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

    @Autowired
    private UserService userService;

    @Autowired
    private Config config;

    private String dateType = "yyyy-MM-dd-HH-mm";


    @Override
    public void initModel(Model model, HttpServletRequest request, HttpSession session)
    {
        this.request = request;
        this.session = session;
        getNewAjax();
    }


    @RequestMapping("/userBike")
    public Object userBikeByUserId(Integer pageNo)
    {



        PageRequest pageRequest = new PageRequest(pageNo,5);

       org.springframework.data.domain.Page<UserBikeFitSession> bikeFitSessionPage = garminBikeService.userBikeByUserId(getUser().getId(), pageRequest);

        for (UserBikeFitSession userBikeFitSession:bikeFitSessionPage)
        {
            userBikeFitSession.setLongTime(RelativeDateFormat.format(userBikeFitSession.getStartTime()));

        }


        return bikeFitSessionPage;
    }

    @RequestMapping("/bikeMsgById/{id}")
    public Object bikeMsgById(@PathVariable String id)
    {
    //    UserBikeFitSession userBikeFitSession = garminBikeService.bikeById(id);

        UserBikeFitSession userBikeFitSession = garminService.getBikeFitSession(config.getUploadFilePath() + "admin/2015-11-12-08-23-23.fit");



        return userBikeFitSession;

    }


    @RequestMapping("/toupload")
    public String toUploadFit() {
        return "garminbike/upload";
    }


    @RequestMapping("/upload")
    public Object fitUpload(@RequestParam MultipartFile file,HttpSession session,HttpServletResponse response) {
        try
        {

            ajaxEntity.setErrors("上传失败");


            UserBikeFitSession userBikeFitSession = garminService.getBikeFitSession(file.getInputStream());
            userBikeFitSession.setUser(getUser());

            String filePath = getUser().getUserName() + "/" + DateUtil.addressRadom(userBikeFitSession.getStartTime()) ;
            String fileName = DateUtil.timeToString(userBikeFitSession.getStartTime(),dateType) + DateUtil.imgType(file.getOriginalFilename());
            String fileUploadPath = config.getUploadFilePath() + filePath + fileName;



            userBikeFitSession.setFitPath(filePath+fileName);

            //保存上传文件
            int result = serviceUtil.uploadFile(file,fileUploadPath);

            //获取地图图片并且下载
            List<GeoPoint> geoPointList = userBikeFitSession.getGarminBike().getLocation();
            int listSite = geoPointList.size();
            int beiShuNum = listSite/50;
            GeoPoint centerGeoPint = geoPointList.get(listSite / 2);
            StringBuilder url = new StringBuilder(config.getBaiduImg());
            url.append("?width=1024&height=170");
         //   url.append("&zoom=11&center=" +centerGeoPint.getLon() + "," + centerGeoPint.getLat());
            url.append("&copyright=1");
            url.append("&pathStyles=0xff0000,3,1");
            url.append("&paths=");

            //坐标转化
            StringBuilder zuoBiao = new StringBuilder();
            zuoBiao.append("http://api.map.baidu.com/geoconv/v1/?from=1&to=5&output=json&ak=A4145e587f79e35cb7029f060282ef98&coords=");
            for (int i = 1; i <=listSite; i+=beiShuNum)
            {
                GeoPoint dianGepoint = geoPointList.get(i-1);
                zuoBiao.append(dianGepoint.getLon() + "," + dianGepoint.getLat() + ";");
            }
            zuoBiao.setLength(zuoBiao.length() - 1);
            RestTemplate restTemplate = new RestTemplate();
            String resultString = restTemplate.getForObject(zuoBiao.toString(), String.class);
            GeoconvResult geoconvResult =  JSON.parseObject(resultString, GeoconvResult.class);

            if(geoconvResult.getStatus() == 0)
            {
                for(PointXY pointXY:geoconvResult.getResult())
                {
                    url.append(pointXY.getX()+","+pointXY.getY()+";");
                }
            }
            String imgName = DateUtil.generateFileName("img.jpg");
            DownImg.saveUrlAs(url.toString(),config.getUploadFilePath() + filePath + imgName);
            userBikeFitSession.setImgUrl(filePath + imgName);


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
