package com.tzy.simplifyweibo.support;

import android.app.Activity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by skylineTan on 2016/5/10 22:05.
 */
public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.add(activity);
    }

    public static void finishAll(){
        for(Activity activity : activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
