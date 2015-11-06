package com.bike.entity.garmin;

/**
 * Created by zz on 2015/9/15.
 */
public class LapBikeParam
{
    private Float maxTime,minTime;

    private UserBikeFitSession userBikeFitSession;

    public Float getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Float maxTime) {
        this.maxTime = maxTime;
    }

    public Float getMinTime() {
        return minTime;
    }

    public void setMinTime(Float minTime) {
        this.minTime = minTime;
    }

    public UserBikeFitSession getUserBikeFitSession() {
        return userBikeFitSession;
    }

    public void setUserBikeFitSession(UserBikeFitSession userBikeFitSession) {
        this.userBikeFitSession = userBikeFitSession;
    }
}
