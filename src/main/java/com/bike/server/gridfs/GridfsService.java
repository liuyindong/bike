package com.bike.server.gridfs;

import com.bike.entity.garmin.GarminBike;

/**
 * Created by zz on 2015/9/7.
 */
public interface GridfsService
{
    public boolean saveFile(GarminBike garminBike, String fileName, String contentType);
}
