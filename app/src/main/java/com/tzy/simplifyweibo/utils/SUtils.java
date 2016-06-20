package com.tzy.simplifyweibo.utils;

/**
 * Created by skylineTan on 2016/5/29 12:44.
 */
public class SUtils {

    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0 || str.equalsIgnoreCase("null") || str.isEmpty() || str.equals("")) {
            return true;
        } else {
            return false;
        }
    }
}
