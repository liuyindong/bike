package com.bike.server.garmin;

import com.bike.entity.garmin.GarminBike;
import com.bike.entity.garmin.UserBikeFitSession;
import com.mongodb.gridfs.GridFSDBFile;
import org.elasticsearch.search.suggest.Suggest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by zz on 2015/9/6.
 */
public interface GarminBikeService
{
    public void addGarminBikeFitSession(UserBikeFitSession userBikeFitSession);

    public Page<UserBikeFitSession> userBikeByUserId(String id,Pageable pageable);

    public void updateBikeGroup(String[] uploadName,String groupType,String groupNum,String remarks);

    public UserBikeFitSession bikeById(String id);



}
