package com.bike.server.NewsType;

import com.bike.entity.NewsType;

import java.util.List;

/**
 * Created by Administrator on 2015/1/22.
 */
public interface NewsTypeServer
{
    List<NewsType> newsTypeList();

    NewsType getNewsType(String typeUUID);


}
