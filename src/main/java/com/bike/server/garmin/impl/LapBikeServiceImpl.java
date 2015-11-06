package com.bike.server.garmin.impl;

import com.bike.entity.garmin.GarminBike;
import com.bike.entity.garmin.LapBike;
import com.bike.entity.garmin.LapBikeParam;
import com.bike.server.ServerBase;
import com.bike.server.garmin.LapBikeService;
import com.bike.util.GarminUtil;
import com.garmin.fit.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by zz on 2015/9/15.
 */
@Service
public class LapBikeServiceImpl extends ServerBase implements LapBikeService
{
    @Override
    public List<LapBike> getLapBike(LapBikeParam lapBikeParam)
    {
        List<LapBike> lapBikes = new ArrayList<>();

        for (LapBike lapMesg : lapBikeParam.getUserBikeFitSession().getLapBikeList())
        {

            // lapMesg.getTotalTimerTime().compareTo(maxTime) == 0
            if (lapBikeParam.getMaxTime() == null || (lapMesg.getTotalTimerTime() <= lapBikeParam.getMaxTime() && lapMesg.getTotalTimerTime() >=lapBikeParam.getMinTime()))
            {

                int startIndex = 0;
                int endIndex = lapBikeParam.getUserBikeFitSession().getGarminBike().getTimestamp().size();


                for (int i = 0; i < lapBikeParam.getUserBikeFitSession().getGarminBike().getTimestamp().size(); i++)
                {
                    Date recordTime = lapBikeParam.getUserBikeFitSession().getGarminBike().getTimestamp().get(i);
                    if (recordTime.equals(lapMesg.getStartTime())) {
                        startIndex = i;
                    } else if (recordTime.equals(lapMesg.getEndTime())) {
                        endIndex = i;
                        break;
                    }
                }


                Map<Integer, Integer> interValTime = new HashMap<>();

                for (Short heartRate : lapBikeParam.getUserBikeFitSession().getGarminBike().getHeartRate().subList(startIndex, endIndex)) {

                    if (StringUtils.isEmpty(heartRate)) {
                        continue;
                    }

                    int interval = GarminUtil.judge(heartRate);

                    Integer value = interValTime.get(interval);
                    if (value == null) {
                        value = 1;
                    } else {
                        value += 1;
                    }
                    interValTime.put(interval, value);
                }

                lapMesg.setHeartRateInterval(interValTime);


                Map<Integer, Integer> strength = new HashMap<>();
                for (Integer key : interValTime.keySet()) {


                    Integer value = strength.get(key);
                    if (value == null) {
                        value = key * (interValTime.get(key) / 60);
                    } else {
                        value += key * (interValTime.get(key) / 60);
                    }
                    strength.put(key, value);

                }
                lapMesg.setStrength(strength);
                lapBikes.add(lapMesg);
            }

        }



        return lapBikes;
    }

    @Override
    public LapBike getLapBikeStatis(List<LapBike> lapBikes,String userId)
    {

        LapBike lapBikeDto = new LapBike();

        lapBikeDto.setUserId(userId);

        for (LapBike lapBike:lapBikes)
        {
            if(lapBikeDto.getAvgSpeed() == null || lapBikeDto.getAvgSpeed() < lapBike.getAvgSpeed())
            {
                lapBikeDto.setAvgSpeed(lapBike.getAvgSpeed());
            }

            if(lapBikeDto.getMaxSpeed() == null || lapBikeDto.getMaxSpeed() < lapBike.getMaxSpeed())
            {
                lapBikeDto.setMaxSpeed(lapBike.getMaxSpeed());
            }
            if(lapBikeDto.getAvgHeartRate() == null || lapBikeDto.getAvgHeartRate() < lapBike.getAvgHeartRate())
            {
                lapBikeDto.setAvgHeartRate(lapBike.getAvgHeartRate());
            }
            if(lapBikeDto.getMaxHeartRate() == null || lapBikeDto.getMaxHeartRate() < lapBike.getMaxHeartRate())
            {
                lapBikeDto.setMaxHeartRate(lapBike.getMaxHeartRate());
            }
            if(lapBikeDto.getAvgPower() == null || lapBikeDto.getAvgPower() < lapBike.getAvgPower())
            {
                lapBikeDto.setAvgPower(lapBike.getAvgPower());
            }
            if(lapBikeDto.getMaxPower() == null || lapBikeDto.getMaxPower() < lapBike.getMaxPower())
            {
                lapBikeDto.setMaxPower(lapBike.getMaxPower());
            }
            if(lapBikeDto.getAvgCadence() == null || lapBikeDto.getAvgCadence() < lapBike.getAvgCadence())
            {
                lapBikeDto.setAvgCadence(lapBike.getAvgCadence());
            }
            if(lapBikeDto.getMaxCadence() == null || lapBikeDto.getMaxCadence() < lapBike.getMaxCadence())
            {
                lapBikeDto.setMaxCadence(lapBike.getMaxCadence());
            }

        }
       return lapBikeDto;

    }

    @Override
    public void addBikeStatis(LapBike lapBike) {
        mongoTemplate.save(lapBike);
    }

    @Override
    public List<LapBike> lapBikeStatisByUser(Integer userId)
    {
        return mongoTemplate.find(query(where("userId").is(userId)),LapBike.class);
    }
}
