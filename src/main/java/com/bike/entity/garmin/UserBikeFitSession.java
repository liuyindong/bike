package com.bike.entity.garmin;

import com.garmin.fit.SessionMesg;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.List;

/**
 * Created by zz on 2015/9/16.
 */
@Document(indexName = "userbikefitsession",type = "userbike" , shards = 1, indexStoreType = "memory", replicas = 0, refreshInterval = "-1")
public class UserBikeFitSession
{
    @Id
    private String id;

    //上传时间
    private Date createDate = new Date();

    private Integer groupNumber;

    private String groupType;

    private String topLapBikeId;

    @GeoPointField
    private List<GeoPoint> bikePoint;

    @Field(type = FieldType.String)
    private String userId;

    private GarminBike garminBike;

    private List<LapBike> lapBikeList;

    private String fitPath ;


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

    private Date startTime;
    private Date endTime;

    private String remarks;

    @Version
    private Long version;



    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTotalTimerHHmmSS() {
        return totalTimerHHmmSS;
    }

    public void setTotalTimerHHmmSS(String totalTimerHHmmSS) {
        this.totalTimerHHmmSS = totalTimerHHmmSS;
    }

    public String getTopLapBikeId() {
        return topLapBikeId;
    }

    public void setTopLapBikeId(String topLapBikeId) {
        this.topLapBikeId = topLapBikeId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public List<LapBike> getLapBikeList() {
        return lapBikeList;
    }

    public void setLapBikeList(List<LapBike> lapBikeList) {
        this.lapBikeList = lapBikeList;
    }
    public String getFitPath() {
        return fitPath;
    }

    public void setFitPath(String fitPath) {
        this.fitPath = fitPath;
    }

    public GarminBike getGarminBike() {
        return garminBike;
    }

    public void setGarminBike(GarminBike garminBike) {
        this.garminBike = garminBike;
    }

    public Float getTotalTimerTime() {
        return totalTimerTime;
    }

    public void setTotalTimerTime(Float totalTimerTime) {
        this.totalTimerTime = totalTimerTime;
    }

    public Float getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Float totalDistance) {
        this.totalDistance = totalDistance;
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public List<GeoPoint> getBikePoint() {
        return bikePoint;
    }

    public void setBikePoint(List<GeoPoint> bikePoint) {
        this.bikePoint = bikePoint;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
