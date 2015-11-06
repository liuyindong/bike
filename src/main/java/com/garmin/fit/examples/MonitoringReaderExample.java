////////////////////////////////////////////////////////////////////////////////
// The following FIT Protocol software provided may be used with FIT protocol
// devices only and remains the copyrighted property of Dynastream Innovations Inc.
// The software is being provided on an "as-is" basis and as an accommodation,
// and therefore all warranties, representations, or guarantees of any kind
// (whether express, implied or statutory) including, without limitation,
// warranties of merchantability, non-infringement, or fitness for a particular
// purpose, are specifically disclaimed.
//
// Copyright 2015 Dynastream Innovations Inc.
////////////////////////////////////////////////////////////////////////////////
// ****WARNING****  This file is auto-generated!  Do NOT edit this file.
// Profile Version = 16.00Release
// Tag = development-akw-16.00.00-0
////////////////////////////////////////////////////////////////////////////////


package com.garmin.fit.examples;

import com.garmin.fit.*;
import com.garmin.fit.csv.MesgCSVWriter;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.Collection;

public class MonitoringReaderExample implements MonitoringMesgListener  {
   private MesgCSVWriter mesgWriter;

   public static void main(String[] args) {
      MonitoringReaderExample example = new MonitoringReaderExample();
      String inputFile;
      int interval;
      System.out.printf("FIT Monitoring Reader Example Application - Protocol %d.%d Profile %.2f %s\n", Fit.PROTOCOL_VERSION_MAJOR, Fit.PROTOCOL_VERSION_MINOR, Fit.PROFILE_VERSION / 100.0, Fit.PROFILE_TYPE);
      if (args.length != 2) {
         System.out
               .println("Usage: java -jar MonitoringReaderExample.jar <input file>.fit <interval>");
         return;
      }
      
      inputFile = args[0];
      interval = Integer.parseInt(args[1]);
      
      example.Process(inputFile, interval, false);

      if (interval == MonitoringReader.DAILY_INTERVAL)
         example.Process(inputFile, interval, true);
      
      System.out.println("Decoded monitoring data from " + args[0] + " to at " + args[1] + "s intervals.");
   }

   public void Process(String inputFile, int interval, boolean dailyTotals)
   {
      Decode decode;
      MonitoringReader monitoringReader;
      MesgBroadcaster mesgBroadcaster;
      FileInputStream inputStream;
      Collection<byte[]> files;
      int fileNum;

      try {
         inputStream = new FileInputStream(inputFile);
         files = FileUtil.split(inputStream);
         files = FileUtil.prepend(files, File.SETTINGS, File.MONITORING_B);
         inputStream.close();
      } catch (java.io.IOException e) {
         throw new RuntimeException("Error opening file " + inputFile);
      }

      fileNum = 1;
      for (byte[] file : files) {
         ByteArrayInputStream fileStream = new ByteArrayInputStream(file);

         decode = new Decode();
         mesgBroadcaster = new MesgBroadcaster(decode);
         monitoringReader = new MonitoringReader(interval);
         if (dailyTotals)
            monitoringReader.outputDailyTotals();
         mesgBroadcaster.addListener((MonitoringInfoMesgListener) monitoringReader);
         mesgBroadcaster.addListener((MonitoringMesgListener) monitoringReader);
         mesgBroadcaster.addListener((DeviceSettingsMesgListener) monitoringReader);
         monitoringReader.addListener(this);

         try {
            String outputFile = inputFile + "-f" + fileNum;
            
            if (dailyTotals)
               outputFile += "-dailyTotals";
            else
               outputFile += "-i" + interval;
            
            outputFile += ".csv";
               
            mesgWriter = new MesgCSVWriter(outputFile);
            mesgBroadcaster.run(fileStream); // Run decoder.
            monitoringReader.broadcast(); // End of file so flush pending data.
            mesgWriter.close();
         } catch (FitRuntimeException e) {
            System.err.print("Exception decoding file: ");
            System.err.println(e.getMessage());
         }

         fileNum++;
      }
   }

   public void onMesg(MonitoringMesg mesg) {
      mesgWriter.onMesg(mesg);
   }
}