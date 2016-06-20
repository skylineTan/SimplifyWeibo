package com.tzy.simplifyweibo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by skylineTan on 2016/5/31 11:39.
 */
public class KeyGenerator {
    private KeyGenerator() {
    }

    public static String generateMD5(String key) {
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.update(key.getBytes());
            byte[] bytes = e.digest();
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < bytes.length; ++i) {
                String hex = Integer.toHexString(255 & bytes[i]);
                if(hex.length() == 1) {
                    sb.append('0');
                }

                sb.append(hex);
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException var6) {
            return String.valueOf(key.hashCode());
        }
    }
}
