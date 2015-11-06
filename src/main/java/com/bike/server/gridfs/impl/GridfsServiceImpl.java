package com.bike.server.gridfs.impl;

import com.bike.entity.garmin.GarminBike;
import com.bike.server.ServerBase;
import com.bike.server.gridfs.GridfsService;

/**
 * Created by zz on 2015/9/7.
 */
public class GridfsServiceImpl extends ServerBase implements GridfsService
{

    @Override
    public boolean saveFile(GarminBike garminBike, String fileName, String contentType)
    {

        return true;
    }
}
