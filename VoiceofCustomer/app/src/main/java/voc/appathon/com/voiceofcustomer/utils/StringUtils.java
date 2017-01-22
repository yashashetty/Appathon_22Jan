package voc.appathon.com.voiceofcustomer.utils;

import android.content.Context;

/**
 * Created by tanu.rawal on 1/13/2017.
 */
public class StringUtils {

    public static String getStringfrmRes(int resourseID, Context context) {
        String str;
        if (context.getResources().getString(resourseID) != null) {
            str = context.getResources().getString(resourseID);
        } else {
            str = "";
        }
        return str;
    }

}
