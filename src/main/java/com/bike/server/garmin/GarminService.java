package com.bike.server.garmin;

import com.bike.entity.garmin.GarminBike;
import com.bike.entity.garmin.LapBike;
import com.bike.entity.garmin.UserBikeFitSession;

import java.io.InputStream;
import java.util.List;

/**
 * Created by zz on 2015/8/27.
 */
public interface GarminService
{

    public UserBikeFitSession getBikeFitSession(InputStream in);
}
