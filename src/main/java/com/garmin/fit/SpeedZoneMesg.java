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


package com.garmin.fit;


public class SpeedZoneMesg extends Mesg {

   protected static final	Mesg speedZoneMesg;
   static {         
      // speed_zone   
      speedZoneMesg = new Mesg("speed_zone", MesgNum.SPEED_ZONE);
      speedZoneMesg.addField(new Field("message_index", 254, 132, 1, 0, "", false));
      
      speedZoneMesg.addField(new Field("high_value", 0, 132, 1000, 0, "m/s", false));
      
      speedZoneMesg.addField(new Field("name", 1, 7, 1, 0, "", false));
      
   }

   public SpeedZoneMesg() {
      super(Factory.createMesg(MesgNum.SPEED_ZONE));
   }

   public SpeedZoneMesg(final Mesg mesg) {
      super(mesg);
   }


   /**
    * Get message_index field
    *
    * @return message_index
    */
   public Integer getMessageIndex() {
      return getFieldIntegerValue(254, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * Set message_index field
    *
    * @param messageIndex
    */
   public void setMessageIndex(Integer messageIndex) {
      setFieldValue(254, 0, messageIndex, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * Get high_value field
    * Units: m/s
    *
    * @return high_value
    */
   public Float getHighValue() {
      return getFieldFloatValue(0, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * Set high_value field
    * Units: m/s
    *
    * @param highValue
    */
   public void setHighValue(Float highValue) {
      setFieldValue(0, 0, highValue, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * Get name field
    *
    * @return name
    */
   public String getName() {
      return getFieldStringValue(1, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * Set name field
    *
    * @param name
    */
   public void setName(String name) {
      setFieldValue(1, 0, name, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

}
