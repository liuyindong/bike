package com.bike.server.garmin;

import com.bike.entity.garmin.GarminBike;
import com.bike.entity.garmin.LapBike;
import com.bike.entity.garmin.LapBikeParam;

import java.util.List;

/**
 * Created by zz on 2015/9/15.
 */
public interface LapBikeService
{
    public List<LapBike> getLapBike(LapBikeParam lapBikeParam);

    //本组最大数据
    public LapBike getLapBikeStatis(List<LapBike> lapBikes,String userId);

    public void addBikeStatis(LapBike lapBike);

    public List<LapBike> lapBikeStatisByUser(Integer userId);
}
