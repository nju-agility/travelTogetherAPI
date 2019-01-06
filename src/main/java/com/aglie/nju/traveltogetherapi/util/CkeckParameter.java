package com.aglie.nju.traveltogetherapi.util;

import com.aglie.nju.traveltogetherapi.model.ActivityInfo;

public class CkeckParameter {
    public static Boolean checkActivity(ActivityInfo activity){
        if (activity.getOwner() == null || activity.getType() == null ||
                activity.getCity() == null || activity.getLocation() == null ||
                activity.getTitle() == null || activity.getDetails() == null ||
                activity.getTime_start() == null || activity.getTime_end() == null
                ){
            return false;
        }
        return true;
    }
}
