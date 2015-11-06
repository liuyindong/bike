package com.bike.entity.garmin;

import com.garmin.fit.LapMesg;
import com.github.abel533.echarts.json.GsonOption;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.*;

/**
 * Created by zz on 2015/9/8.
 */
@Document
public class LapBike implements Serializable
{
    private String id = UUID.randomUUID().toString();

    //总时间
    private Float totalTimerTime;

    private String totalTimerHHmmSS;

    //总距离
    private Float totalDistance;

    private Float avgSpeed;

    private Float maxSpeed;

    private Short avgHeartRate;

    private Short maxHeartRate;

    //平均踏频
    private Short avgCadence;
    private Short maxCadence;

    private Integer avgPower;
    private Integer maxPower;

    //总卡里路
    private Integer totalCalories;

    //坐标
    private GeoPoint startPosition;
    private GeoPoint endPosition;

    private Date startTime;
    private Date endTime;

    public String getTotalTimerHHmmSS() {
        return totalTimerHHmmSS;
    }

    public void setTotalTimerHHmmSS(String totalTimerHHmmSS) {
        this.totalTimerHHmmSS = totalTimerHHmmSS;
    }

    //强度
    private Map<Integer,Integer> strength;

    private Map<Integer,Integer> heartRateInterval;

    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<Integer, Integer> getStrength() {
        return strength;
    }

    public void setStrength(Map<Integer, Integer> strength) {
        this.strength = strength;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public GeoPoint getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(GeoPoint startPosition) {
        this.startPosition = startPosition;
    }

    public GeoPoint getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(GeoPoint endPosition) {
        this.endPosition = endPosition;
    }

    public Map<Integer, Integer> getHeartRateInterval() {
        return heartRateInterval;
    }

    public void setHeartRateInterval(Map<Integer, Integer> heartRateInterval) {
        this.heartRateInterval = heartRateInterval;
    }

    public Float getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Float totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Float getTotalTimerTime() {
        return totalTimerTime;
    }

    public void setTotalTimerTime(Float totalTimerTime) {
        this.totalTimerTime = totalTimerTime;
    }

    public Float getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(Float avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Short getAvgHeartRate() {
        return avgHeartRate;
    }

    public void setAvgHeartRate(Short avgHeartRate) {
        this.avgHeartRate = avgHeartRate;
    }

    public Short getMaxHeartRate() {
        return maxHeartRate;
    }

    public void setMaxHeartRate(Short maxHeartRate) {
        this.maxHeartRate = maxHeartRate;
    }

    public Short getAvgCadence() {
        return avgCadence;
    }

    public void setAvgCadence(Short avgCadence) {
        this.avgCadence = avgCadence;
    }

    public Short getMaxCadence() {
        return maxCadence;
    }

    public void setMaxCadence(Short maxCadence) {
        this.maxCadence = maxCadence;
    }

    public Integer getAvgPower() {
        return avgPower;
    }

    public void setAvgPower(Integer avgPower) {
        this.avgPower = avgPower;
    }

    public Integer getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(Integer maxPower) {
        this.maxPower = maxPower;
    }

    public Integer getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(Integer totalCalories) {
        this.totalCalories = totalCalories;
    }

    public void addHeartRateInterval(int interVal,Integer time)
    {
        if (this.heartRateInterval == null) {
            this.heartRateInterval = new HashMap<>(1);
        }
        this.heartRateInterval.put(interVal,time);
    }
}
