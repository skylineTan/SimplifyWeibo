package com.tzy.simplifyweibo.support;

import com.tzy.simplifyweibo.bean.StatusWrapper;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by skylineTan on 2016/5/24 09:29.
 */
public interface ApiService {

    String BASE_URL = "https://api.weibo.com/2/";

    @GET(Constants.API.GET_FRIENDS_STATUS)
    Observable<StatusWrapper> getStatusList(
            @Query("access_token") String access_token,
            @Query("page") int page,@Query("count") int count);
}
