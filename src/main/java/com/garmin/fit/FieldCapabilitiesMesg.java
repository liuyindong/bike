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


package com.garmin.fit;


public class FieldCapabilitiesMesg extends Mesg {

   protected static final	Mesg fieldCapabilitiesMesg;
   static {         
      // field_capabilities   
      fieldCapabilitiesMesg = new Mesg("field_capabilities", MesgNum.FIELD_CAPABILITIES);
      fieldCapabilitiesMesg.addField(new Field("message_index", 254, 132, 1, 0, "", false));
      
      fieldCapabilitiesMesg.addField(new Field("file", 0, 0, 1, 0, "", false));
      
      fieldCapabilitiesMesg.addField(new Field("mesg_num", 1, 132, 1, 0, "", false));
      
      fieldCapabilitiesMesg.addField(new Field("field_num", 2, 2, 1, 0, "", false));
      
      fieldCapabilitiesMesg.addField(new Field("count", 3, 132, 1, 0, "", false));
      
   }

   public FieldCapabilitiesMesg() {
      super(Factory.createMesg(MesgNum.FIELD_CAPABILITIES));
   }

   public FieldCapabilitiesMesg(final Mesg mesg) {
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
    * Get file field
    *
    * @return file
    */
   public File getFile() {
      Short value = getFieldShortValue(0, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
      if (value == null)
         return null;
      return File.getByValue(value);
   }

   /**
    * Set file field
    *
    * @param file
    */
   public void setFile(File file) {
      setFieldValue(0, 0, file.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * Get mesg_num field
    *
    * @return mesg_num
    */
   public Integer getMesgNum() {
      return getFieldIntegerValue(1, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * Set mesg_num field
    *
    * @param mesgNum
    */
   public void setMesgNum(Integer mesgNum) {
      setFieldValue(1, 0, mesgNum, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * Get field_num field
    *
    * @return field_num
    */
   public Short getFieldNum() {
      return getFieldShortValue(2, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * Set field_num field
    *
    * @param fieldNum
    */
   public void setFieldNum(Short fieldNum) {
      setFieldValue(2, 0, fieldNum, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * Get count field
    *
    * @return count
    */
   public Integer getCount() {
      return getFieldIntegerValue(3, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * Set count field
    *
    * @param count
    */
   public void setCount(Integer count) {
      setFieldValue(3, 0, count, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

}
