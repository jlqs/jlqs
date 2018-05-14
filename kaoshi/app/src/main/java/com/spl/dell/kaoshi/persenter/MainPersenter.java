package com.spl.dell.kaoshi.persenter;

import com.spl.dell.kaoshi.model.MainModel;
import com.spl.dell.kaoshi.model.bean.ResultBean;
import com.spl.dell.kaoshi.persenter.interfaces.MainPerInter;
import com.spl.dell.kaoshi.view.interfaces.BaseMainInterface;

public class MainPersenter implements MainPerInter {

    private BaseMainInterface baseMainInterface;
    private MainModel mainModel;

    public MainPersenter(BaseMainInterface baseMainInterface) {
        this.baseMainInterface = baseMainInterface;
        mainModel = new MainModel(this);
    }




    @Override
    public void onSuccess(ResultBean resultBean) {
        baseMainInterface.onSuccess(resultBean);
    }

    @Override
    public void onError(Throwable throwable) {
        baseMainInterface.onError(throwable);
    }

    @Override
    public void getData(String path) {
        mainModel.getData(path);
    }
}
