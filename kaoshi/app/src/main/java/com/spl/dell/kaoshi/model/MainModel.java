package com.spl.dell.kaoshi.model;

import com.google.gson.Gson;
import com.spl.dell.kaoshi.model.bean.ResultBean;
import com.spl.dell.kaoshi.model.net.ApiService;
import com.spl.dell.kaoshi.model.net.BaseUrl;
import com.spl.dell.kaoshi.model.net.HttpUtils;
import com.spl.dell.kaoshi.persenter.interfaces.BasePersenter;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MainModel implements BaseModel{
    private BasePersenter basePersenter;

    public MainModel(BasePersenter basePersenter) {
        this.basePersenter = basePersenter;
    }


    @Override
    public void getData(String path) {
        HttpUtils.getInstance(path).createService(ApiService.class)
                .doGet(BaseUrl.homePage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ResponseBody value) {
                        try {
                            String json = value.string();
                            ResultBean resultBean = new Gson().fromJson(json, ResultBean.class);
                            basePersenter.onSuccess(resultBean);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        basePersenter.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
