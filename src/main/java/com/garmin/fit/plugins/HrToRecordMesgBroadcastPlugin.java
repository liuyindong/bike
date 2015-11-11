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

package com.garmin.fit.plugins;

import java.util.List;
import java.lang.Math;

import com.garmin.fit.*;

/**
 * Provides functionality to backfill record messages with hr data from HR mesg.
 * <p>
 * The plugin matches the timestamp of record messages with the timestamps hr data
 * contained in the HR mesg.
 * <p>
 * Requirements for correct operation:
 *    - HR data must be in the order of increasing timestamp
 *    - Record data must be in the order of increasing timestamp
 *    - The order of incoming HR and record mesgs may be independent of each other
 *    - The first HR mesg must contain a timestamp (with optional fractional_timestamp)
 *      which, in combination with the event_timestamps that mark the bpm data, provide the
 *      time that is used to match record data.
 *    - There must be an equal number of filtered_bpm fields and event_timestamp fields in each
 *      HR mesg; this number may change from message to message.
 *
 */
public class HrToRecordMesgBroadcastPlugin implements MesgBroadcastPlugin {
   // Invalid index constant
   private static final int   INVALID_INDEX = -1;

   // Record data is valid over a range of time, this variable holds the lower
   // end of that range.
   private DateTime record_range_start_time;

   // Counts the number of incoming messages
   private int mesg_count;

   // Index tracker of current HR mesg that is being, or will be searched, for next record message
   private int hr_start_index;

   // Index tracker of current event within the current HR mesg that is being, or will be searched, for next record message
   private int hr_start_sub_index;

   // Marks if we are processing an activity file
   private boolean isActivityFile;

   /**
    * Constructor
    *
    * Performs variable initilization
    *
    */
   public HrToRecordMesgBroadcastPlugin() {
      record_range_start_time = new DateTime(0);
      hr_start_index          = INVALID_INDEX;
      hr_start_sub_index      = INVALID_INDEX;
      isActivityFile          = false;
      mesg_count              = 0;
   }

   /**
    * Peeks messages as they are being added to the buffer
    *
    * @param mesg the message that has just been buffered by BufferedMesgBroadcaster
    */
   public void onIncomingMesg(final Mesg mesg) {
      switch (mesg.getNum()) {

         case MesgNum.FILE_ID:
            // Check to see if we are processing an activity file.
            final FileIdMesg fileIdMesg = new FileIdMesg(mesg);
            if(fileIdMesg.getType() == File.ACTIVITY)
               isActivityFile = true;
            break;

         case MesgNum.SESSION:
            // Obtain session start time to mark the start of the first
            // record message's time range
            final SessionMesg sessionMesg = new SessionMesg(mesg);
            record_range_start_time = new DateTime(sessionMesg.getStartTime());
            break;

         case MesgNum.HR:
            if( hr_start_index == HrToRecordMesgBroadcastPlugin.INVALID_INDEX ) {
               // Mark the first appearance of an HR message
               hr_start_index = mesg_count;
               hr_start_sub_index = 0;
            }
            break;

         default:
            break;

      } // switch

      mesg_count++;
   }

   /**
    * Matches record time ranges with all time matching HR mesgs and updates the
    * message stream for later broadcast to listeners.
    *
    * @param mesgs the message list that is about to be broadcast to all MesgListeners.  \
                   Note: The List is 'final' but the references within the list are not, \
                   therefore editing Mesg objects within mesgs will alter the messages   \
                   that are broadcast to listeners.
    *
    * DO NOT add or remove any messages to mesgs
    */
   public void onBroadcast(final List<Mesg> mesgs) {

      // Check if we have an activity file and have received HR messages
      if( isActivityFile && (hr_start_index != HrToRecordMesgBroadcastPlugin.INVALID_INDEX) ) {
         Float hr_anchor_event_timestamp = new Float(0.0);
         DateTime hr_anchor_timestamp = new DateTime(0);
         boolean hr_anchor_set = false;
         short last_valid_hr = 0;
         DateTime last_valid_hr_time = new DateTime(0);

         for (int mesgCounter = 0; mesgCounter < mesgs.size(); ++mesgCounter) {
            Mesg mesg = mesgs.get(mesgCounter);

            // Process any record messages we encounter
            if(mesg.getNum() == MesgNum.RECORD) {
               long hrSum = 0;
               long hrSumCount = 0;

               // Cast message to record message
               RecordMesg recordMesg = new RecordMesg(mesg);

               // Obtain the time for which the record message is valid
               DateTime record_range_end_time = new DateTime(recordMesg.getTimestamp());

               // Need to determine timestamp range which applies to this record
               boolean findingInRangeHrMesgs = true;

               // Start searching HR mesgs where we left off
               int hr_mesg_counter = hr_start_index;
               int hr_sub_mesg_counter = hr_start_sub_index;

               while(findingInRangeHrMesgs && (hr_mesg_counter < mesgs.size())) {

                  // Skip over any non HR messages
                  if(mesgs.get(hr_mesg_counter).getNum() == MesgNum.HR) {
                     HrMesg hrMesg = new HrMesg(mesgs.get(hr_mesg_counter));

                     // Update HR timestamp anchor, if present
                     if(hrMesg.getTimestamp() != null) {
                        hr_anchor_timestamp = new DateTime(hrMesg.getTimestamp());
                        hr_anchor_set = true;

                        if(hrMesg.getFractionalTimestamp() != null)
                           hr_anchor_timestamp.add(hrMesg.getFractionalTimestamp());

                        if(hrMesg.getNumEventTimestamp() == 1) {
                            hr_anchor_event_timestamp = hrMesg.getEventTimestamp(0);
                        }
                        else {
                            throw new FitRuntimeException("FIT HrToRecordMesgBroadcastPlugin Error: Anchor HR mesg must have 1 event_timestamp");
                        }
                     }

                     if(hr_anchor_set == false) {
                        // We cannot process any HR messages if we have not received a timestamp anchor
                        throw new FitRuntimeException("FIT HrToRecordMesgBroadcastPlugin Error: No anchor timestamp received in a HR mesg before diff HR mesgs");
                     }
                     else if(hrMesg.getNumEventTimestamp() != hrMesg.getNumFilteredBpm()) {
                        throw new FitRuntimeException("FIT HrToRecordMesgBroadcastPlugin Error: HR mesg with mismatching event timestamp and filtered bpm");
                     }
                     for(int j = hr_sub_mesg_counter; j < hrMesg.getNumEventTimestamp(); j++) {

                        // Build up timestamp for each message using the anchor and event_timestamp
                        DateTime hrMesgTime = new DateTime(hr_anchor_timestamp);
                        Float event_timestamp = hrMesg.getEventTimestamp(j);

                        // Deal with roll over case
                        if(event_timestamp < hr_anchor_event_timestamp)	{
                            if ((hr_anchor_event_timestamp - event_timestamp) > ( 1 << 21 )) {
                                event_timestamp += ( 1 << 22 );
                            }
                        else {
                                throw new FitRuntimeException("FIT HrToRecordMesgBroadcastPlugin Error: Anchor event_timestamp is greater than subsequent event_timestamp. This does not allow for correct delta calculation.");
                            }
                        }

                        hrMesgTime.add(event_timestamp - hr_anchor_event_timestamp);

                        // Check if hrMesgTime is gt record start time
                        // and if hrMesgTime is lte to record end time
                        if((hrMesgTime.compareTo(record_range_start_time) > 0) &&
                           (hrMesgTime.compareTo(record_range_end_time) <= 0)) {
                           hrSum += hrMesg.getFilteredBpm(j);
                           hrSumCount++;
                           last_valid_hr_time = new DateTime(hrMesgTime);

                        }
                        // check if hrMesgTime exceeds the record time
                        else if(hrMesgTime.compareTo(record_range_end_time) > 0) {

                           // Remember where we left off
                           hr_start_index = hr_mesg_counter;
                           hr_start_sub_index = j;
                           findingInRangeHrMesgs = false;

                           if(hrSumCount > 0) {
                              // Update record heart rate
                              last_valid_hr = (short)Math.round((((float)hrSum) / hrSumCount));
                              recordMesg.setHeartRate(last_valid_hr);
                              mesgs.set(mesgCounter, (Mesg)recordMesg);
                           }
                           // If no stored HR is available, fill in record messages with the
                           // last valid filtered hr for a maximum of 5 seconds
                           else if((record_range_start_time.compareTo(last_valid_hr_time) > 0) &&
                                   ((record_range_start_time.getTimestamp() - last_valid_hr_time.getTimestamp()) < 5)) {
                              recordMesg.setHeartRate(last_valid_hr);
                              mesgs.set(mesgCounter, (Mesg)recordMesg);
                           }

                           // Reset HR average
                           hrSum = 0;
                           hrSumCount = 0;

                           record_range_start_time = new DateTime(record_range_end_time);

                           // Breaks out of looping within the event_timestamp array
                           break;
                        }
                     }
                  }
                  hr_mesg_counter++;
                  hr_sub_mesg_counter = 0;
               }
            }
         }
      }
   }
}
