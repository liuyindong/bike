package com.bike.server.garmin.impl;

import com.bike.entity.garmin.UserBikeFitSession;
import com.bike.server.ServerBase;
import com.bike.server.garmin.GarminService;
import com.bike.util.fit.GarminBikeFitListener;
import com.garmin.fit.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;


@Service
public class GarminServiceImpl extends ServerBase implements GarminService
{


    @Override
    public UserBikeFitSession getBikeFitSession(InputStream in)
    {
        GarminBikeFitListener listener = new GarminBikeFitListener();
        Decode decode = new Decode();
        MesgBroadcaster mesgBroadcaster = new MesgBroadcaster(decode);
        mesgBroadcaster.addListener((SessionMesgListener) listener);
        mesgBroadcaster.addListener((RecordMesgListener) listener);
        mesgBroadcaster.addListener((LapMesgListener) listener);

        try {
            mesgBroadcaster.run(in);
        } catch (FitRuntimeException e) {

            try {
                in.close();
            } catch (IOException f) {
                throw new RuntimeException(f);
            }

            return null;
        }

        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return listener.getUserBikeFitSessionMesg();
    }


}
