package com.aglie.nju.traveltogetherapi.util;

import com.aglie.nju.traveltogetherapi.model.ActivityInfo;
import com.aglie.nju.traveltogetherapi.model.TravelNoteInfo;

/**
 * 检查请求的参数是否完全
 */
public class CkeckParameter {
    public static Boolean checkActivity(ActivityInfo activity){
        return activity.getOwner() != null && activity.getType() != null &&
                activity.getCity() != null && activity.getLocation() != null &&
                activity.getTitle() != null && activity.getDetails() != null &&
                activity.getTime_start() != null && activity.getTime_end() != null &&
                activity.getPrice() != null;
    }

    public static boolean checkTravelNote(TravelNoteInfo noteInfo){
        if(noteInfo.getAccount() == null || noteInfo.getCity() == null ||
        noteInfo.getDetails() == null || noteInfo.getLocation() == null ||
        noteInfo.getTitle() == null || noteInfo.getSubmission_date() == null){
            return false;
        }
        return true;
    }
}
