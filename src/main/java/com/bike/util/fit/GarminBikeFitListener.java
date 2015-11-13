package com.bike.util.fit;


import com.bike.util.WritePath;
import com.bike.util.map.GaoDeGPS;
import com.bike.entity.garmin.GarminBike;
import com.bike.entity.garmin.LapBike;
import com.bike.entity.garmin.UserBikeFitSession;
import com.bike.util.GarminUtil;
import com.bike.util.MyConstants;
import com.garmin.fit.*;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class GarminBikeFitListener implements  SessionMesgListener,RecordMesgListener,LapMesgListener
{

    private UserBikeFitSession userBikeFitSession;

    private GarminBike garminBike;

    private List<LapBike> lapBikes;


    public GarminBikeFitListener() {
        super();
        this.userBikeFitSession = new UserBikeFitSession();
        this.garminBike = new GarminBike();
        this.lapBikes = new ArrayList<>();
    }




    @Override
    public void onMesg(SessionMesg sessionMesg)
    {
        userBikeFitSession.setTotalTimerTime(sessionMesg.getTotalTimerTime());
        userBikeFitSession.setTotalTimerHHmmSS(MyConstants.secToTime(sessionMesg.getTotalTimerTime().intValue()));

        userBikeFitSession.setTotalDistance(MyConstants.mTokm(sessionMesg.getTotalDistance()));

        userBikeFitSession.setAvgSpeed(MyConstants.msTokmh(sessionMesg.getAvgSpeed()));
        userBikeFitSession.setMaxSpeed(MyConstants.msTokmh(sessionMesg.getMaxSpeed()));

        userBikeFitSession.setAvgHeartRate(sessionMesg.getAvgHeartRate());
        userBikeFitSession.setMaxHeartRate(sessionMesg.getMaxHeartRate());

        userBikeFitSession.setAvgCadence(sessionMesg.getAvgCadence());
        userBikeFitSession.setMaxCadence(sessionMesg.getMaxCadence());

        userBikeFitSession.setAvgPower(sessionMesg.getAvgPower());
        userBikeFitSession.setMaxPower(sessionMesg.getMaxPower());

        userBikeFitSession.setStartTime(sessionMesg.getStartTime().getDate());
        userBikeFitSession.setEndTime(sessionMesg.getTimestamp().getDate());

    }

    public UserBikeFitSession getUserBikeFitSessionMesg()
    {
        if(userBikeFitSession.getId() == null)
        {
            userBikeFitSession.setId(UUID.randomUUID().toString());
        }

        userBikeFitSession.setGarminBike(garminBike);
        userBikeFitSession.setLapBikeList(lapBikes);
        return this.userBikeFitSession;
    }


    @Override
    public void onMesg(RecordMesg recordMesg)
    {
        garminBike.addTimestamp(recordMesg.getTimestamp().getDate());
        garminBike.addDistance(recordMesg.getDistance() != null ? MyConstants.mTokm(recordMesg.getDistance()) : 0);
        garminBike.addHeartRate(recordMesg.getHeartRate() != null ? recordMesg.getHeartRate() : 0);
        garminBike.addSpeed(recordMesg.getSpeed() != null ? MyConstants.msTokmh(recordMesg.getSpeed()) : 0);
        garminBike.addPower(recordMesg.getPower() != null ? recordMesg.getPower() : 0);
        garminBike.addCadence(recordMesg.getCadence() != null ? recordMesg.getCadence() : 0);
        garminBike.addTemperature(recordMesg.getTemperature() != null ? recordMesg.getTemperature() : 0);
        garminBike.addAltitude(recordMesg.getAltitude() != null ? recordMesg.getAltitude() : 0);
        garminBike.addgrade(recordMesg.getGrade() != null ? recordMesg.getGrade() : 0);

    //    garminBike.addCycleLength(recordMesg.getCycleLength() != null ?recordMesg.getCycleLength() : 0);
    //    garminBike.addCycles(recordMesg.getCycles() != null ? recordMesg.getCycles() : 0);
        if (recordMesg.getPositionLat() != null && recordMesg.getPositionLong() != null)
        {
            garminBike.addLocation(GaoDeGPS.transformFromWGSToGCJ(new GeoPoint(GarminUtil.toDegrees(recordMesg.getPositionLat()),GarminUtil.toDegrees(recordMesg.getPositionLong()))));
        }

    }

    @Override
    public void onMesg(LapMesg mesg)
    {
        LapBike lapBike = new LapBike();
        lapBike.setTotalDistance(MyConstants.mTokm(mesg.getTotalDistance()));
        lapBike.setTotalTimerTime(mesg.getTotalTimerTime());
        lapBike.setTotalTimerHHmmSS(MyConstants.secToTime(mesg.getTotalTimerTime().intValue()));
        lapBike.setAvgSpeed(MyConstants.msTokmh(mesg.getAvgSpeed()));
        lapBike.setMaxSpeed(MyConstants.msTokmh(mesg.getMaxSpeed()));
        lapBike.setAvgHeartRate(mesg.getAvgHeartRate());
        lapBike.setMaxHeartRate(mesg.getMaxHeartRate());
        lapBike.setAvgCadence(mesg.getAvgCadence());
        lapBike.setMaxCadence(mesg.getMaxCadence());
        lapBike.setAvgPower(mesg.getAvgPower());
        lapBike.setMaxPower(mesg.getMaxPower());
        lapBike.setTotalCalories(mesg.getTotalCalories());
        lapBike.setStartPosition(GaoDeGPS.transformFromWGSToGCJ(new GeoPoint(GarminUtil.toDegrees(mesg.getStartPositionLat()),GarminUtil.toDegrees(mesg.getStartPositionLong()))));
        lapBike.setEndPosition(GaoDeGPS.transformFromWGSToGCJ(new GeoPoint(GarminUtil.toDegrees(mesg.getEndPositionLat()),GarminUtil.toDegrees(mesg.getEndPositionLong()))));
        lapBike.setStartTime(mesg.getStartTime().getDate());
        lapBike.setEndTime(mesg.getTimestamp().getDate());

        lapBikes.add(lapBike);

    }
}
