package com.bike.entity.baidu;

import org.springframework.data.geo.Point;

import java.util.List;

/**
 * Created by liuyindong on 2015/11/10.
 */
public class GeoconvResult
{
    private int status;

    private List<PointXY> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<PointXY> getResult() {
        return result;
    }

    public void setResult(List<PointXY> result) {
        this.result = result;
    }
}
