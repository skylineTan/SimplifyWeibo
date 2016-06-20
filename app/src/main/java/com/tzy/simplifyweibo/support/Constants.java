package com.tzy.simplifyweibo.support;

/**
 * Created by skylineTan on 2016/5/19 12:40.
 */
public class Constants {

    public static final String APP_KEY = "4257071592";

    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";

    public static final String SCOPE = "";


    public static class API{
        public static final String GET_FRIENDS_STATUS = "statuses/friends_timeline.json";
    }

    public static class Scheme{
        public static final String TOPICS = "com.tzy.simplifyweibo.topics://";
        public static final String MENTIONS = "com.tzy.simplifyweibo" +
                ".mentions://";
    }
}
