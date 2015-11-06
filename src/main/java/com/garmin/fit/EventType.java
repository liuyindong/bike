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

public enum EventType {
   START((short)0),
   STOP((short)1),
   CONSECUTIVE_DEPRECIATED((short)2),
   MARKER((short)3),
   STOP_ALL((short)4),
   BEGIN_DEPRECIATED((short)5),
   END_DEPRECIATED((short)6),
   END_ALL_DEPRECIATED((short)7),
   STOP_DISABLE((short)8),
   STOP_DISABLE_ALL((short)9),
   INVALID((short)255);


   protected short value;




   private EventType(short value) {
     this.value = value;
   }

   public static EventType getByValue(final Short value) {
      for (final EventType type : EventType.values()) {
         if (value == type.value)
            return type;
      }

      return EventType.INVALID;
   }

   public short getValue() {
      return value;
   }


}
