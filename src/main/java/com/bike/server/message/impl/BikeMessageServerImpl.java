package com.bike.server.message.impl;


import com.bike.entity.*;
import com.bike.server.ServerBase;
import com.bike.server.jsoup.JsoupServer;
import com.bike.server.message.BikeMessageServer;
import com.bike.server.qiniu.QiNiuServer;
import com.bike.server.tags.TagServer;
import com.bike.util.Config;
import com.bike.util.DateUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


/**
 * Created by ld on 2015/1/15.
 */
@Service
public class BikeMessageServerImpl extends ServerBase implements BikeMessageServer {

    private Logger logger = LoggerFactory.getLogger(BikeMessageServerImpl.class);

    private final String collectionName = "bikeMessage";

    private  final  String xlmName = "NewsMapper.";

    @Autowired
    private Config config;

    @Autowired
    private JsoupServer jsoupServer;

    @Autowired
    private QiNiuServer qiNiuServer;

    @Autowired
    private TagServer tagServer;

    @Override
    public void add(BikeMessages bikeMessages)
    {
        mongoTemplate.insert(bikeMessages,collectionName);
    }

    @Override
    public BikeMessages messageShow(String uuid)
    {
        updateShowNum(uuid);
        return mongoTemplate.findOne(query(where("uuid").is(uuid)), BikeMessages.class, collectionName);
    }

    @Override
    public void updateShowNum(String uuid) {
        mongoTemplate.updateFirst(query(where("uuid").is(uuid)), new Update().inc("showNum", 1), collectionName);
    }

    @Override
    public Page getPages(int pageNo)
    {
        return this.getPageUtil(pageNo,new Query().with(new Sort(new Sort.Order(Sort.Direction.DESC, "createTime"))),collectionName,BikeMessages.class);

    }


    @Async
    @Override
    public void addMSql(BikeMessages bikeMessage)
    {


        bikeMessage.setUuid(UUID.randomUUID().toString());

        Document newContent = Jsoup.parse(bikeMessage.getContent());

        switch (bikeMessage.getShowTypeId())
        {
            case 1:
                bikeMessage.setDisplayForm(newContent.select("embed.edui-faked-video").first().attr("width", "513").attr("height", "293").toString());
                break;
            case 2:
                bikeMessage.setDisplayForm(newContent.select("embed.edui-faked-music").first().attr("width", "513").attr("height", "293").toString());
                break;
            case 3:
                String imgNew = newContent.select("img").first().attr("src")+"?imageMogr2/thumbnail/513x293!";
                imgNew = "<img src='"+imgNew+"' alt='"+bikeMessage.getTitle()+"' alt='"+bikeMessage.getTitle()+"' />";
                bikeMessage.setDisplayForm(imgNew);
                bikeMessage.setFrontcoverImg(imgNew);
        }
        String contentText = Jsoup.clean(bikeMessage.getContent(),Whitelist.none());
        if(contentText.length() > 200)
            contentText = contentText.substring(0,200) + "...";

        bikeMessage.setSummary(contentText);
        bikeMessage.setCreateTime(new Date());
        bikeMessage.setStatusId(1);

        myBatisDao.insert(xlmName + "add",bikeMessage);



        List<BikeTag> bikeTagList = tagServer.learnTags(bikeMessage);

        bikeMessage.setBikeTagList(bikeTagList);

        //mysql也添加下标签
        tagServer.addListTags(bikeTagList);

    }

    @Override
    public MysqlPags messageByType(String typeUUID,String tagUUID,int pageNo)
    {

        this.getNewPag(pageNo);

        this.map.put("typeUUID",typeUUID);
        this.map.put("tagUUID",tagUUID);
        mysqlPags.setTotalCount((Integer) myBatisDao.selectOne(xlmName + "newsByTJCount",map));
        mysqlPags.setDatas(myBatisDao.selectList(xlmName  + "newsByTJ", map));


        return mysqlPags;
        // this.getPageUtil(pageNo,query(where("typeUuid").is(uuid)),collectionName,BikeMessages.class);
    }

    @Override
    public Page newByTJSelect(int pageNo,String typeUUID,String tagUUID)
    {
        Criteria criteria = new Criteria();
        if(!StringUtils.isEmpty(tagUUID))
        {
            criteria.and("bikeTagList").elemMatch(Criteria.where("uuid").in(tagUUID));
        }

           if(!StringUtils.isEmpty(typeUUID))
       {
           criteria.and("typeUuid").is(typeUUID);
       }

       return this.getPageUtil(pageNo,query(criteria).with(new Sort(new Sort.Order(Sort.Direction.DESC, "createTime"))), collectionName, BikeMessages.class);

    }

    @Override
    public List<BikeMessages> bikeMessageList(BikeMessages bikeMessages)
    {

        return myBatisDao.selectList(xlmName + "new_list",bikeMessages);
    }


    @Override
    public List<BikeMessages> bikeNewsXiangSi(List<String> tags,String newUUID)
    {

        Criteria criteria = Criteria.where("bikeTagList").elemMatch(Criteria.where("uuid").in(tags));

        return mongoTemplate.find(query(criteria.and("uuid").nin(newUUID)).limit(10), BikeMessages.class, collectionName);


    //    return bikeMessageList();
    }

    @Override
    public List<BikeMessages> showTopTen()
    {
        return mongoTemplate.find(new Query().with(new Sort(new Sort.Order(Sort.Direction.DESC, "showNum"))).limit(10),BikeMessages.class, collectionName);
    }

    @Override
    public void updateBikeNew(BikeMessages bikeMessages)
    {
        myBatisDao.update(xlmName + "updateNew",bikeMessages);

    }

    @Override
    public void newUpdateStatusId(String uuid, int statusId)
    {
        getNewMap();
        map.put("uuid",uuid);
        map.put("statusId",statusId);
        myBatisDao.update(xlmName + "updateStatus",map);
    }


    @Override
    public List<BikeMessages> crawlNews(CrwNews crwNews)
    {


        List<BikeMessages> bikeMessagesList = new ArrayList<>();

        try
        {
            Document document = jsoupServer.getDocument(crwNews.getCrwUrl());

            Elements msgList = document.select("div.lg-w800>div.lg-w800>div.mix-col1");


            for (Element element : msgList)
            {
                BikeMessages bikeMessages = new BikeMessages();

                bikeMessages.setUuid(UUID.randomUUID().toString());

                String title = element.select("div.mix-col1-hd a").first().text();
                bikeMessages.setTitle(title);




                Document msgDocument = jsoupServer.getDocument(element.select("div.mix-col1-left a").first().attr("abs:href")+"?all=1#page_0");


                Element newEnd1 = msgDocument.select("div.article-wrap .article-content").first();

                newEnd1.select("p[style*=text-indent:2em;text-align:right]").remove();
                newEnd1.select("p[style*=text-align:right;text-indent:2em]").remove();

                Elements pngs = newEnd1.select("img[src]");
                for (Element element2 : pngs)
                {
                    String newsContImgUrl = element2.attr("abs:src");

                    BikeImg bikeImg1 = qiNiuServer.imgFetch(newsContImgUrl, config.getBucket(), DateUtil.addressRadom(null) + DateUtil.generateFileName(newsContImgUrl));

                    element2.attr("src", bikeImg1.getImgUrl());
                    element2.attr("class", "img-responsive");

                }

                String imgUrl = newEnd1.select("img[src]").first().attr("abs:src");


                BikeImg bikeImg = qiNiuServer.imgFetch(imgUrl, config.getBucket(), DateUtil.addressRadom(null) +  DateUtil.generateFileName(imgUrl));
                bikeMessages.setFrontcoverImg(bikeImg.getImgUrl());
                bikeMessages.setDisplayForm("<img src=\"" + bikeImg.getImgUrl() + "?imageMogr2/thumbnail/513x293!\"  alt=\"" + title + "\" title=\"" + title + "\"/>");


                bikeMessages.setContent(Jsoup.clean(newEnd1.outerHtml(), Whitelist.basicWithImages()));


                String contentText = Jsoup.clean(newEnd1.outerHtml(),Whitelist.none());
                if(contentText.length() > 200)
                    contentText = contentText.substring(0,200) + "...";

                bikeMessages.setSummary(contentText);

                bikeMessagesList.add(bikeMessages);

                List<BikeTag> bikeTagList = tagServer.learnTags(bikeMessages);

                bikeMessages.setBikeTagList(bikeTagList);

                //mysql也添加下标签
                tagServer.addListTags(bikeTagList);

                logger.info(bikeMessages.getTitle());

            }


        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return bikeMessagesList;
    }


}
