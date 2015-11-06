package com.bike.entity.test;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.geo.Point;

import java.util.Date;
import java.util.List;

import static org.springframework.data.elasticsearch.annotations.FieldType.Float;
import static org.springframework.data.elasticsearch.annotations.FieldType.Integer;

/**
 * Created by liuyindong on 2015/11/4.
 */
@Document(indexName = "garminBike",type = "garminbike" , shards = 1, indexStoreType = "memory", replicas = 0, refreshInterval = "-1")
public class TestFitMsg
{
    @Id
    private String id;

    @Version
    private Long version;


    //距离
    @Field(type = Float, store = true)
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
    private List<GeoPoint> geoPoints;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<java.lang.Float> getDistance() {
        return distance;
    }

    public void setDistance(List<java.lang.Float> distance) {
        this.distance = distance;
    }

    public List<Short> getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(List<Short> heartRate) {
        this.heartRate = heartRate;
    }

    public List<java.lang.Float> getSpeed() {
        return speed;
    }

    public void setSpeed(List<java.lang.Float> speed) {
        this.speed = speed;
    }

    public List<java.lang.Integer> getPower() {
        return power;
    }

    public void setPower(List<java.lang.Integer> power) {
        this.power = power;
    }

    public List<Short> getCadence() {
        return cadence;
    }

    public void setCadence(List<Short> cadence) {
        this.cadence = cadence;
    }

    public List<Byte> getTemperature() {
        return temperature;
    }

    public void setTemperature(List<Byte> temperature) {
        this.temperature = temperature;
    }

    public List<java.lang.Float> getAltitude() {
        return altitude;
    }

    public void setAltitude(List<java.lang.Float> altitude) {
        this.altitude = altitude;
    }

    public List<GeoPoint> getGeoPoints() {
        return geoPoints;
    }

    public void setGeoPoints(List<GeoPoint> geoPoints) {
        this.geoPoints = geoPoints;
    }

    public List<Short> getCycles() {
        return cycles;
    }

    public void setCycles(List<Short> cycles) {
        this.cycles = cycles;
    }

    public List<java.lang.Float> getCycleLength() {
        return cycleLength;
    }

    public void setCycleLength(List<java.lang.Float> cycleLength) {
        this.cycleLength = cycleLength;
    }

    public List<Date> getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(List<Date> timestamp) {
        this.timestamp = timestamp;
    }
}
