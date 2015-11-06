package com.bike.entity.garmin;

import com.garmin.fit.DateTime;
import com.garmin.fit.LapMesg;
import com.garmin.fit.RecordMesg;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zz on 2015/8/27.
 */
@org.springframework.data.elasticsearch.annotations.Document(indexName = "garminBike",type = "garminbike" , shards = 1, indexStoreType = "memory", replicas = 0, refreshInterval = "-1")
public class GarminBike
{
    @Id
    private String id;

    //距离
    private List<Float> distance;
    //心率
    private List<Short> heartRate;
    //速度
    private List<Float> speed;
    //功率
    private List<Integer> power;
    //踏频
    private List<Short> cadence;
    //温度
    private List<Byte> temperature;
    //海拔
    private List<Float> altitude;
    //坐标
    @GeoPointField
    private List<GeoPoint> location;
    //
    private List<Short> cycles;
    private List<Float> cycleLength;
    //时间
    private List<Date> timestamp;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Short> getCycles() {
        return cycles;
    }

    public void setCycles(List<Short> cycles) {
        this.cycles = cycles;
    }

    public List<Date> getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(List<Date> timestamp) {
        this.timestamp = timestamp;
    }

    public List<Float> getCycleLength() {
        return cycleLength;
    }

    public void setCycleLength(List<Float> cycleLength) {
        this.cycleLength = cycleLength;
    }

    public List<GeoPoint> getLocation() {
        return location;
    }

    public void setLocation(List<GeoPoint> location) {
        this.location = location;
    }

    public List<Float> getAltitude() {
        return altitude;
    }

    public void setAltitude(List<Float> altitude) {
        this.altitude = altitude;
    }

    public List<Byte> getTemperature() {
        return temperature;
    }

    public void setTemperature(List<Byte> temperature) {
        this.temperature = temperature;
    }

    public List<Short> getCadence() {
        return cadence;
    }

    public void setCadence(List<Short> cadence) {
        this.cadence = cadence;
    }

    public List<Integer> getPower() {
        return power;
    }

    public void setPower(List<Integer> power) {
        this.power = power;
    }

    public List<Float> getSpeed() {
        return speed;
    }

    public void setSpeed(List<Float> speed) {
        this.speed = speed;
    }

    public List<Short> getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(List<Short> heartRate) {
        this.heartRate = heartRate;
    }

    public List<Float> getDistance() {
        return distance;
    }

    public void setDistance(List<Float> distance) {
        this.distance = distance;
    }



  /*  public void addRecordMesg(final RecordMesg recordMesg)
    {
        if (this.recordMesgList == null) {
            this.recordMesgList = new ArrayList<>(1);
        }
        this.recordMesgList.add(recordMesg);
    }*/

    public void addDistance(final Float distance)
    {
        if (this.distance == null) {
            this.distance = new ArrayList<>(1);
        }
        this.distance.add(distance);
    }
    public void addHeartRate(final Short heartReate)
    {
        if (this.heartRate == null) {
            this.heartRate = new ArrayList<>(1);
        }
        this.heartRate.add(heartReate);
    }

    public void addSpeed(final Float speed)
    {
        if (this.speed == null) {
            this.speed = new ArrayList<>(1);
        }
        this.speed.add(speed);
    }
    public void addPower(final Integer power)
    {
        if (this.power == null) {
            this.power = new ArrayList<>(1);
        }
        this.power.add(power);
    }

    public void addCadence(final Short cadence)
    {
        if (this.cadence == null) {
            this.cadence = new ArrayList<>(1);
        }
        this.cadence.add(cadence);
    }

    public void addTemperature(final Byte temperature)
    {
        if (this.temperature == null) {
            this.temperature = new ArrayList<>(1);
        }
        this.temperature.add(temperature);
    }
    public void addAltitude(final Float altitude)
    {
        if (this.altitude == null) {
            this.altitude = new ArrayList<>(1);
        }
        this.altitude.add(altitude);
    }

    public void addLocation(final double lat,final double lon)
    {
        if (this.location == null) {
            this.location = new ArrayList<>(1);
        }
        this.location.add(new GeoPoint(lat,lon));
    }
    public void addCycleLength(final float cycleLength)
    {
        if (this.cycleLength == null) {
            this.cycleLength = new ArrayList<>(1);
        }
        this.cycleLength.add(cycleLength);
    }
    public void addTimestamp(Date timestamp)
    {
        if (this.timestamp == null) {
            this.timestamp = new ArrayList<>(1);
        }
        this.timestamp.add(timestamp);
    }
    public void addCycles(Short cycles)
    {
        if (this.cycles == null) {
            this.cycles = new ArrayList<>(1);
        }
        this.cycles.add(cycles);
    }




}
