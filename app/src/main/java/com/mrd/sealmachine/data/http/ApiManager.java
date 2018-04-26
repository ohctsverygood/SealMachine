package com.mrd.sealmachine.data.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2018/4/12.
 */

public class ApiManager {

    private static OkHttpClient driverApiClient;

    public static OkHttpClient getDriverApi()
    {
        synchronized (driverApiClient) {
            if (driverApiClient == null)
                driverApiClient = new  OkHttpClient.Builder().
                        connectTimeout(5, TimeUnit.SECONDS)
                        .readTimeout(5,TimeUnit.SECONDS)
                        .writeTimeout(5,TimeUnit.SECONDS)
                        .build();

                return driverApiClient;
        }
    }


}
