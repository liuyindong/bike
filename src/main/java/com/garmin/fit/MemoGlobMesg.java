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


public class MemoGlobMesg extends Mesg {

   protected static final	Mesg memoGlobMesg;
   static {         
      // memo_glob   
      memoGlobMesg = new Mesg("memo_glob", MesgNum.MEMO_GLOB);
      memoGlobMesg.addField(new Field("part_index", 250, 134, 1, 0, "", false));
      
      memoGlobMesg.addField(new Field("memo", 0, 13, 1, 0, "", false));
      
      memoGlobMesg.addField(new Field("message_number", 1, 132, 1, 0, "", false));
      
      memoGlobMesg.addField(new Field("message_index", 2, 132, 1, 0, "", false));
      
   }

   public MemoGlobMesg() {
      super(Factory.createMesg(MesgNum.MEMO_GLOB));
   }

   public MemoGlobMesg(final Mesg mesg) {
      super(mesg);
   }


   /**
    * Get part_index field
    * Comment: Sequence number of memo blocks
    *
    * @return part_index
    */
   public Long getPartIndex() {
      return getFieldLongValue(250, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * Set part_index field
    * Comment: Sequence number of memo blocks
    *
    * @param partIndex
    */
   public void setPartIndex(Long partIndex) {
      setFieldValue(250, 0, partIndex, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * @return number of memo
    */
   public int getNumMemo() {
      return getNumFieldValues(0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * Get memo field
    * Comment: Block of utf8 bytes 
    *
    * @param index of memo
    * @return memo
    */
   public Byte getMemo(int index) {
      return getFieldByteValue(0, index, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * Set memo field
    * Comment: Block of utf8 bytes 
    *
    * @param index of memo
    * @param memo
    */
   public void setMemo(int index, Byte memo) {
      setFieldValue(0, index, memo, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * Get message_number field
    * Comment: Allows relating glob to another mesg  If used only required for first part of each memo_glob
    *
    * @return message_number
    */
   public Integer getMessageNumber() {
      return getFieldIntegerValue(1, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * Set message_number field
    * Comment: Allows relating glob to another mesg  If used only required for first part of each memo_glob
    *
    * @param messageNumber
    */
   public void setMessageNumber(Integer messageNumber) {
      setFieldValue(1, 0, messageNumber, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * Get message_index field
    * Comment: Index of external mesg
    *
    * @return message_index
    */
   public Integer getMessageIndex() {
      return getFieldIntegerValue(2, 0, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

   /**
    * Set message_index field
    * Comment: Index of external mesg
    *
    * @param messageIndex
    */
   public void setMessageIndex(Integer messageIndex) {
      setFieldValue(2, 0, messageIndex, Fit.SUBFIELD_INDEX_MAIN_FIELD);
   }

}
