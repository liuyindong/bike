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


public class MesgCapabilitiesMesg extends Mesg {

   protected static final	Mesg mesgCapabilitiesMesg;
   static {            
      int field_index = 0;
      int subfield_index = 0;         
      // mesg_capabilities   
      mesgCapabilitiesMesg = new Mesg("mesg_capabilities", MesgNum.MESG_CAPABILITIES);
      mesgCapabilitiesMesg.addField(new Field("message_index", 254, 132, 1, 0, "", false));
      field_index++;
      mesgCapabilitiesMesg.addField(new Field("file", 0, 0, 1, 0, "", false));
      field_index++;
      mesgCapabilitiesMesg.addField(new Field("mesg_num", 1, 132, 1, 0, "", false));
      field_index++;
      mesgCapabilitiesMesg.addField(new Field("count_type", 2, 0, 1, 0, "", false));
      field_index++;
      mesgCapabilitiesMesg.addField(new Field("count", 3, 132, 1, 0, "", false));
      subfield_index = 0;
      mesgCapabilitiesMesg.fields.get(field_index).subFields.add(new SubField("num_per_file", 132, 1, 0, ""));
      mesgCapabilitiesMesg.fields.get(field_index).subFields.get(subfield_index).addMap(2, 0);
      subfield_index++;
      mesgCapabilitiesMesg.fields.get(field_index).subFields.add(new SubField("max_per_file", 132, 1, 0, ""));
      mesgCapabilitiesMesg.fields.get(field_index).subFields.get(subfield_index).addMap(2, 1);
      subfield_index++;
      mesgCapabilitiesMesg.fields.get(field_index).subFields.add(new SubField("max_per_file_type", 132, 1, 0, ""));
      mesgCapabilitiesMesg.fields.get(field_index).subFields.get(subfield_index).addMap(2, 2);
      subfield_index++;
      field_index++;
   }

   public MesgCapabilitiesMesg() {
      super(Factory.createMesg(MesgNum.MESG_CAPABILITIES));
   }

   public MesgCapabilitiesMesg(final Mesg mesg) {
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
    * Get count_type field
    *
    * @return count_type
    */
   public MesgCount getCountType() {
      Short value = getFieldShortValue(2, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
      if (value == null)
         return null;
      return MesgCount.getByValue(value);
   }

   /**
    * Set count_type field
    *
    * @param countType
    */
   public void setCountType(MesgCount countType) {
      setFieldValue(2, 0, countType.value, Fit.SUBFIELD_INDEX_MAIN_FIELD);
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

   /**
    * Get num_per_file field
    *
    * @return num_per_file
    */
   public Integer getNumPerFile() {
      return getFieldIntegerValue(3, 0, Profile.SubFields.MESG_CAPABILITIES_MESG_COUNT_FIELD_NUM_PER_FILE);
   }

   /**
    * Set num_per_file field
    *
    * @param numPerFile
    */
   public void setNumPerFile(Integer numPerFile) {
      setFieldValue(3, 0, numPerFile, Profile.SubFields.MESG_CAPABILITIES_MESG_COUNT_FIELD_NUM_PER_FILE);
   }

   /**
    * Get max_per_file field
    *
    * @return max_per_file
    */
   public Integer getMaxPerFile() {
      return getFieldIntegerValue(3, 0, Profile.SubFields.MESG_CAPABILITIES_MESG_COUNT_FIELD_MAX_PER_FILE);
   }

   /**
    * Set max_per_file field
    *
    * @param maxPerFile
    */
   public void setMaxPerFile(Integer maxPerFile) {
      setFieldValue(3, 0, maxPerFile, Profile.SubFields.MESG_CAPABILITIES_MESG_COUNT_FIELD_MAX_PER_FILE);
   }

   /**
    * Get max_per_file_type field
    *
    * @return max_per_file_type
    */
   public Integer getMaxPerFileType() {
      return getFieldIntegerValue(3, 0, Profile.SubFields.MESG_CAPABILITIES_MESG_COUNT_FIELD_MAX_PER_FILE_TYPE);
   }

   /**
    * Set max_per_file_type field
    *
    * @param maxPerFileType
    */
   public void setMaxPerFileType(Integer maxPerFileType) {
      setFieldValue(3, 0, maxPerFileType, Profile.SubFields.MESG_CAPABILITIES_MESG_COUNT_FIELD_MAX_PER_FILE_TYPE);
   }

}