package com.spl.dell.kaoshi.model.net;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("{url}")
    Observable<ResponseBody> doGet(@Path(value = "url",encoded = true)String path);


}
