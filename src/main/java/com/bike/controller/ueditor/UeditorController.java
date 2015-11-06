package com.bike.controller.ueditor;

import com.baidu.ueditor.ActionEnter;
import com.bike.controller.ControllerBase;
import com.bike.entity.BikeImg;
import com.bike.entity.uedit.ImgList;
import com.bike.entity.uedit.UeditoreEntity;
import com.bike.server.qiniu.QiNiuServer;
import com.bike.server.ueditor.UeditorServer;
import com.bike.util.DateUtil;
import com.qiniu.api.auth.AuthException;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Administrator on 2015/1/31.
 */
@Controller
@RequestMapping("/uedtor/*")
public class UeditorController extends ControllerBase
{

    @Autowired
    private UeditorServer ueditorServer;

    @RequestMapping(value = "/init")
    @ResponseBody
    public Object uedtorInit(Model model,HttpServletRequest request,String action) throws JSONException
    {

        return new ActionEnter( request, request.getServletContext().getRealPath("/WEB-INF/classes/mapper") ).exec();
    //    return   + "config.json";
        //return new UeditoreEntity();

    }

    @RequestMapping(value = "/init",params={"action=uploadimage"})
    @ResponseBody
    public Object uploadImage(MultipartFile upfile ) throws Exception {

        return  ueditorServer.uploadFile(upfile);

    }

    @RequestMapping(value = "/init",params={"action=listimage"})
    @ResponseBody
    public Object imgList(ImgList imgList)
    {
       return ueditorServer.listImg(imgList);
    }




}
