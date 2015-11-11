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
// Profile Version = 16.30Release
// Tag = development-akw-16.30.00-0
////////////////////////////////////////////////////////////////////////////////


package com.garmin.fit.test;

import com.garmin.fit.*;
import java.util.ArrayList;

public class MesgIndexTest implements Test, MesgListener {
   private String szError = "";
   private ArrayList<MesgIndexList> MesgIndexLists; // array of messages objects

   public MesgIndexTest() {
      MesgIndexLists = new ArrayList<MesgIndexList>();
   }

   // each mesg type has an instance of object MesgIndexList to keep track of it's indexes
   private class MesgIndexList {
      private String szName; // name of message
      private ArrayList<Integer> MesgIndexes; // array of indexes

      MesgIndexList(String szName) {
         MesgIndexes = new ArrayList<Integer>();

         this.szName = szName;
      }

      public void add(int index) {
         MesgIndexes.add(index);
      }

      public boolean contains(int index) {
         return MesgIndexes.contains(index);
      }

      public String getName() {
         return szName;
      }

      public String verify() {
         int maxIndex;
         if (MesgIndexes.isEmpty())
            return "Message contains no indexes";

         // get max index value
         maxIndex = MesgIndexes.get(0);
         for (int i = 0; i < MesgIndexes.size(); i++) {
            if (MesgIndexes.get(i) > maxIndex)
               maxIndex = MesgIndexes.get(i);
         }

         // verify all indexes below max are present
         if (maxIndex == 0)
            return null;
         do {
            maxIndex--;
            if (!MesgIndexes.contains(maxIndex))
               return "Message index " + maxIndex + " missing from " + szName + " messages.";
         } while (maxIndex > 0);

         return null;
      }
   }

   public void onMesg(Mesg mesg) {
      Field stField; // used to extract "message_index" field from message
      Object stList[] = MesgIndexLists.toArray(); // used to access array elements

      if (szError != "")
         return;

      stField = mesg.getField("message_index");
      if (stField == null) // if field does not exists in message
         return;

      if (containsMesg(mesg.getName())) {
         int index = getIndex(mesg.getName());

         if (((MesgIndexList) stList[index]).contains(Integer.parseInt(stField.getValue().toString())))
            szError = "Mesg " + mesg.getName() + " already contains index " + stField.getValue();
         ((MesgIndexList) stList[index]).add(Integer.parseInt(stField.getValue().toString()));
      } else {
         MesgIndexList stNewList = new MesgIndexList(mesg.getName());

         stNewList.add(Integer.parseInt(stField.getValue().toString())); // add index to list
         MesgIndexLists.add(stNewList); // add list
      }
   }

   private boolean containsMesg(String szName) {
      if (MesgIndexLists.isEmpty())
         return false;

      for (int i = 0; i < MesgIndexLists.size(); i++) {
         if (MesgIndexLists.get(i).getName() == szName)
            return true;
      }
      return false;
   }

   private int getIndex(String szName) {
      if (MesgIndexLists.isEmpty())
         return -1;

      for (int i = 0; i < MesgIndexLists.size(); i++) {
         if (MesgIndexLists.get(i).getName() == szName)
            return i;
      }
      return -1;
   }

   public String getError() {
      String szVerifyError;

      if (szError != "")
         return szError;

      for (int i = 0; i < MesgIndexLists.size(); i++) {
         if ((szVerifyError = MesgIndexLists.get(i).verify()) != "")
            return szVerifyError;
      }
      return null;
   }
}
