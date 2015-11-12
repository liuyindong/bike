package com.bike.server.garmin.impl;

import com.bike.entity.garmin.UserBikeFitSession;
import com.bike.server.ServerBase;
import com.bike.server.garmin.GarminService;
import com.bike.util.fit.GarminBikeFitListener;
import com.garmin.fit.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.io.File;


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

    @Override
    public UserBikeFitSession getBikeFitSession(String path)
    {
        FileInputStream in;



        try {
            in = new FileInputStream(path);
        } catch (java.io.IOException e) {
            throw new RuntimeException("Error opening file " + path + " [2]");
        }
        return getBikeFitSession(in);
    }


}
