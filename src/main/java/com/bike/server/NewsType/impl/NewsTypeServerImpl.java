package com.bike.server.NewsType.impl;

import com.bike.entity.NewsType;
import com.bike.server.NewsType.NewsTypeServer;
import com.bike.server.ServerBase;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/1/22.
 */
@Service
public class NewsTypeServerImpl extends ServerBase implements NewsTypeServer
{
    private final String xlmName = "NewTypeMapper.";

    @Override
    public List<NewsType> newsTypeList()
    {

        return myBatisDao.selectList(xlmName + "newTypeList");
    }

    @Override
    public NewsType getNewsType(String typeUUID)
    {
        return myBatisDao.selectOne(xlmName + "typeByUUID",typeUUID);
    }
}
