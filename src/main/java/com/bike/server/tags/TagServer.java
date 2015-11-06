package com.bike.server.tags;

import com.bike.entity.BikeMessages;
import com.bike.entity.BikeTag;
import com.bike.entity.TagNews;

import java.util.List;

/**
 * Created by Administrator on 2015/1/23.
 */
public interface TagServer
{
    int addTags(BikeTag bikeTag);

    void addListTags(List<BikeTag> tagList);

    int updateWeight(BikeTag bikeTag);

    BikeTag tagByName(String name);

    void addTagNews(TagNews tagNews);

    List<BikeTag> listBikeTag();

    List<BikeTag> newsGetTags(BikeMessages bikeMessages);

    List<BikeTag> learnTags(BikeMessages bikeMessages);

    List<BikeTag> tagByNewUUID(String uuid);

    List<BikeTag> topNumTags(int topNum);
}
