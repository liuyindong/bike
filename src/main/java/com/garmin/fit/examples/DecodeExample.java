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
import java.io.FileInputStream;
import java.io.InputStream;

public class DecodeExample {
   private static class Listener implements FileIdMesgListener, UserProfileMesgListener, DeviceInfoMesgListener, MonitoringMesgListener ,RecordMesgListener{

      public void onMesg(FileIdMesg mesg) {
         System.out.println("File ID:");

         if (mesg.getType() != null) {
            System.out.print("   Type: ");
            System.out.println(mesg.getType().getValue());
         }

         if (mesg.getManufacturer() != null) {
            System.out.print("   Manufacturer: ");
            System.out.println(mesg.getManufacturer());
         }

         if (mesg.getProduct() != null) {
            System.out.print("   Product: ");
            System.out.println(mesg.getProduct());
         }

         if (mesg.getSerialNumber() != null) {
            System.out.print("   Serial Number: ");
            System.out.println(mesg.getSerialNumber());
         }

         if (mesg.getNumber() != null) {
            System.out.print("   Number: ");
            System.out.println(mesg.getNumber());
         }
      }

      public void onMesg(UserProfileMesg mesg) {
         System.out.println("User profile:");

         if ((mesg.getFriendlyName() != null) ) {
            System.out.print("   Friendly Name: ");
            System.out.println(mesg.getFriendlyName());
         }

         if (mesg.getGender() != null) {
            if (mesg.getGender() == Gender.MALE)
               System.out.println("   Gender: Male");
            else if (mesg.getGender() == Gender.FEMALE)
               System.out.println("   Gender: Female");
         }

         if (mesg.getAge() != null) {
            System.out.print("   Age [years]: ");
            System.out.println(mesg.getAge());
         }

         if (mesg.getWeight() != null) {
            System.out.print("   Weight [kg]: ");
            System.out.println(mesg.getWeight());
         }
      }

      public void onMesg(DeviceInfoMesg mesg){
         System.out.println("Device info:");

         if(mesg.getTimestamp() != null){
            System.out.print("   Timestamp: ");
            System.out.println(mesg.getTimestamp());
         }

         if(mesg.getBatteryStatus() != null){
            System.out.print("   Battery status: ");

            switch(mesg.getBatteryStatus()){

            case BatteryStatus.CRITICAL:
               System.out.println("Critical");
               break;
            case BatteryStatus.GOOD:
               System.out.println("Good");
               break;
            case BatteryStatus.LOW:
               System.out.println("Low");
               break;
            case BatteryStatus.NEW:
               System.out.println("New");
               break;
            case BatteryStatus.OK:
               System.out.println("OK");
               break;
            default:
               System.out.println("Invalid");
            }
         }
      }

      public void onMesg(MonitoringMesg mesg) {
         System.out.println("Monitoring:");

         if(mesg.getTimestamp() != null){
            System.out.print("   Timestamp: ");
            System.out.println(mesg.getTimestamp());
         }

         if(mesg.getActivityType() != null){
            System.out.print("   Activity Type: ");
            System.out.println(mesg.getActivityType());
         }

         // Depending on the ActivityType, there may be Steps, Strokes, or Cycles present in the file
         if(mesg.getSteps() != null){
            System.out.print("   Steps: ");
            System.out.println(mesg.getSteps());
         }
         else if(mesg.getStrokes() != null){
            System.out.print("   Strokes: ");
            System.out.println(mesg.getStrokes());
         }
         else if (mesg.getCycles() != null){
            System.out.print("   Cycles: ");
            System.out.println(mesg.getCycles());
         }
      }

       @Override
       public void onMesg(RecordMesg mesg)
       {
           System.out.print(mesg);
       }
   }

   public static void main(String[] args1) {
      Decode decode = new Decode();

      MesgBroadcaster mesgBroadcaster = new MesgBroadcaster(decode);
      Listener listener = new Listener();
      FileInputStream in;


       String filePath = "D://bike.fit";

      try {
         in = new FileInputStream(filePath);
      } catch (java.io.IOException e) {
         throw new RuntimeException("Error opening file " + filePath + " [2]");
      }

      mesgBroadcaster.addListener((FileIdMesgListener) listener);
      mesgBroadcaster.addListener((UserProfileMesgListener) listener);
      mesgBroadcaster.addListener((DeviceInfoMesgListener) listener);
      mesgBroadcaster.addListener((MonitoringMesgListener) listener);
       mesgBroadcaster.addListener((RecordMesgListener) listener);

      try {
         mesgBroadcaster.run(in);
      } catch (FitRuntimeException e) {
         System.err.print("Exception decoding file: ");
         System.err.println(e.getMessage());

         try {
            in.close();
         } catch (java.io.IOException f) {
            throw new RuntimeException(f);
         }

         return;
      }

      try {
         in.close();
      } catch (java.io.IOException e) {
         throw new RuntimeException(e);
      }

      System.out.println("Decoded FIT file " + filePath + ".");
   }
}
