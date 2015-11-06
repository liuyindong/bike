package com.bike.server.message;

import com.bike.entity.BikeMessages;
import com.bike.entity.CrwNews;
import com.bike.entity.MysqlPags;
import com.bike.entity.Page;

import java.util.List;

/**
 * Created by ld on 2015/1/15.
 */
public interface BikeMessageServer
{
    /**
     * 添加
     * @param bikeMessages
     */
    void add(BikeMessages bikeMessages);

    //查看信息内容
    BikeMessages messageShow(String uuid);

    //修改查看数量
    void updateShowNum(String uuid);

    Page getPages(int pageNo);

    void addMSql(BikeMessages bikeMessages);

    //根据类型获取内容
    MysqlPags messageByType(String typeUUID,String tagUUID,int pageNo);

    Page newByTJSelect(int pageNo,String typeUUID,String tagUUID);

    List<BikeMessages> bikeMessageList(BikeMessages bikeMessages);

    List<BikeMessages> bikeNewsXiangSi(List<String> tags,String newUUID);

    List<BikeMessages> showTopTen();

    void updateBikeNew(BikeMessages bikeMessages);

    void newUpdateStatusId(String uuid,int statusId);

    //抓取信息
    List<BikeMessages> crawlNews(CrwNews crwNews);




}
