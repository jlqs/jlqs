package com.spl.dell.kaoshi.model.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {
    private static Retrofit retrofit;
    private static HttpUtils httpUtils;
    private HttpUtils(String baseUrl){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static HttpUtils getInstance(String baseUrl){
        if(httpUtils == null){
            synchronized (HttpUtils.class){
                if(httpUtils == null){
                    httpUtils = new HttpUtils(baseUrl);
                }
            }
        }
        return httpUtils;
    }


    public <T>T createService(Class<T> service){
        return retrofit.create(service);
    }


}
