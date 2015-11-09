package com.bike.entity.garmin;

import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.geo.Point;

import java.util.List;

/**
 * Created by liuyindong on 2015/11/3.
 */
public class FitResult
{
    private GeoPoint center;

    private int zoom;

    private List<GeoPoint> paths;

    public GeoPoint getCenter() {
        return center;
    }

    public void setCenter(GeoPoint center) {
        this.center = center;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public List<GeoPoint> getPaths() {
        return paths;
    }

    public void setPaths(List<GeoPoint> paths) {
        this.paths = paths;
    }
}
