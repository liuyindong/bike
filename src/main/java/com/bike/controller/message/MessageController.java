package com.bike.controller.message;

import com.bike.controller.ControllerBase;
import com.bike.entity.BikeMessages;
import com.bike.entity.BikeTag;
import com.bike.entity.CrwNews;
import com.bike.server.NewsType.NewsTypeServer;
import com.bike.server.message.BikeMessageServer;
import com.bike.server.qiniu.QiNiuServer;
import com.bike.server.qrcode.QrCodeServer;
import com.bike.server.tags.TagServer;
import com.bike.util.Config;
import com.bike.util.DateUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by ld on 2015/1/20.
 */
@Controller
@RequestMapping("/message")
public class MessageController extends ControllerBase {
    @Autowired
    private BikeMessageServer bikeMessageServer;

    @Autowired
    private NewsTypeServer newsTypeServer;

    @Autowired
    private QrCodeServer qrCodeServer;

    @Autowired
    private TagServer tagServer;

    @Autowired
    private Config config;

    @Autowired
    private QiNiuServer qiNiuServer;

    public void initModel(Model model, HttpServletRequest request) {
        this.request = request;
    }


    @RequestMapping("/byType")
    public ModelAndView newsByType(String typeUUID, String tagUUID, Integer pagNo) {
        ModelAndView mav = new ModelAndView("message/index");

        mav.addObject("page", bikeMessageServer.newByTJSelect(pagNo, typeUUID, tagUUID));

        mav.addObject("newType", newsTypeServer.newsTypeList());

        mav.addObject("bikeTags", tagServer.topNumTags(10));

        if (!StringUtils.isEmpty(typeUUID)) {
            mav.addObject("bikeType", newsTypeServer.getNewsType(typeUUID));
        }
        mav.addObject("tagUUID", tagUUID);
        mav.addObject("newTopTen", bikeMessageServer.showTopTen());

        return mav;
    }

    @RequestMapping("/item/{uuid}")
    public ModelAndView msgItem(@PathVariable String uuid) {


        ModelAndView mav = new ModelAndView("message/item");

        BikeMessages bikeMessages = bikeMessageServer.messageShow(uuid);


        List<String> listTags = new ArrayList<>();

        for (BikeTag bikeTag : bikeMessages.getBikeTagList()) {
            listTags.add(bikeTag.getUuid());
        }

        mav.addObject("bikeMessages", bikeMessages);

        mav.addObject("newType", newsTypeServer.newsTypeList());

        mav.addObject("newsXiangsi", bikeMessageServer.bikeNewsXiangSi(listTags, bikeMessages.getUuid()));

        return mav;
    }

    public ModelAndView newsByTags() {
        return null;
    }


    /**
     * 新闻二维码
     * @param response
     * @param context
     */
    @RequestMapping("/getQrcode")
    public void scQrcode(HttpServletResponse response, String context)
    {
        try {
            qrCodeServer.getQrCode(request,response,context,268,268);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 新建资讯信息
     *
     * @param bikeMessages
     * @return
     */
    @RequestMapping(value = "addNews", method = RequestMethod.POST)
    public String addNews(BikeMessages bikeMessages, String editorValue) {
        bikeMessages.setContent(editorValue);
        bikeMessageServer.addMSql(bikeMessages);
        return "/admin/news/newsView";
    }

    @RequestMapping("/confirmation/{uuid}")
    public String refNewMongo(@PathVariable String uuid) {
        BikeMessages bikeMessages = new BikeMessages();
        bikeMessages.setUuid(uuid);
        bikeMessages.setStatusId(1);
        List<BikeMessages> bikeMessagesList = bikeMessageServer.bikeMessageList(bikeMessages);
        if (bikeMessagesList.size() > 0)
        {
            bikeMessages = bikeMessagesList.get(0);

            List<BikeTag> bikeTagList = tagServer.tagByNewUUID(bikeMessages.getUuid());

            bikeMessages.setBikeTagList(bikeTagList);

            bikeMessageServer.add(bikeMessages);

            bikeMessageServer.newUpdateStatusId(bikeMessages.getUuid(),0);
        }
        return "/admin/news/newsView";
    }

    @RequestMapping("/getNewType")
    @ResponseBody
    public Object getNewType() {
        return newsTypeServer.newsTypeList();
    }


    @RequestMapping("/crwMsgIng")
    public void crwMsgIng() {
        for (int i = 1; i <= 3; i++) {
            CrwNews crwNews = new CrwNews();


            Map<String, String> map = new HashMap<>();

            map.put("354b46d0-7063-4717-baa7-f9d215534596", "http://www.biketo.com/edge/index_" + i + ".html");
            map.put("9bde2892-c444-4cdd-9f8f-6da9449c06a8", "http://www.biketo.com/product/bikes/index_" + i + ".html");
            map.put("e16aa123-2bba-4910-a8be-ce89fb8edc1f", "http://www.biketo.com/tour/index_" + i + ".html");


            for (Map.Entry<String, String> entry : map.entrySet()) {

                crwNews.setCrwUrl(entry.getValue());
                List<BikeMessages> bikeMessageList = bikeMessageServer.crawlNews(crwNews);

                for (BikeMessages bikeMessages : bikeMessageList) {
                    bikeMessages.setCreateTime(new Date());

                    bikeMessages.setTypeUuid(entry.getKey());


                    try {
                        bikeMessageServer.addMSql(bikeMessages);
                        bikeMessageServer.add(bikeMessages);
                    } catch (Exception e) {
                        continue;
                    }

                }

            }


        }

    }
}
