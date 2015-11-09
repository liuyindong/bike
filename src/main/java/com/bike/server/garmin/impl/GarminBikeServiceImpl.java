package com.bike.server.garmin.impl;

import com.bike.entity.garmin.GarminBike;
import com.bike.entity.garmin.UserBikeFitSession;
import com.bike.repositories.garmin.GarminBikeRepository;
import com.bike.server.ServerBase;
import com.bike.server.garmin.GarminBikeService;
import com.bike.server.garmin.GarminService;
import com.bike.util.Config;
import com.mongodb.gridfs.GridFSDBFile;
import org.elasticsearch.search.suggest.Suggest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;
import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename;

/**
 * Created by zz on 2015/9/6.
 */
@Service
public class GarminBikeServiceImpl extends ServerBase implements GarminBikeService
{

    @Autowired
    private GarminBikeRepository garminBikeRepository;

    @Override
    public void addGarminBikeFitSession(UserBikeFitSession userBikeFitSession)
    {
     //   userBikeFitSession.setBikePoint(userBikeFitSession.getGarminBike().getLocation());
        userBikeFitSession.setGarminBike(null);
        userBikeFitSession.setVersion(System.currentTimeMillis());
        garminBikeRepository.save(userBikeFitSession);
    }

    @Override
    public Page<UserBikeFitSession> userBikeById(String id,Pageable pageable)
    {
        return garminBikeRepository.findByUserIdOrderByStartTimeDesc(id,pageable);
    }

    @Override
    public void updateBikeGroup(String[] uploadName, String groupType, String groupNum,String remarks)
    {
        for (String id:uploadName)
        {
            mongoTemplate.updateFirst(query(where("id").is(id)),new Update().set("groupNumber", groupNum).set("groupType", groupType).set("remarks",remarks),UserBikeFitSession.class);
        }
    }
}
