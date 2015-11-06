package com.bike.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by ld on 2015/1/14.
 */
public class BikeHome
{
   private List<BikeMessages> bikeMessagesList;

    public List<BikeMessages> getBikeMessagesList() {
        return bikeMessagesList;
    }

    public void setBikeMessagesList(List<BikeMessages> bikeMessagesList) {
        this.bikeMessagesList = bikeMessagesList;
    }
}
