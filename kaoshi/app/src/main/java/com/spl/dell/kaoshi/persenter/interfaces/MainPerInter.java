package com.spl.dell.kaoshi.persenter.interfaces;

import com.spl.dell.kaoshi.model.bean.ResultBean;

public interface MainPerInter extends BasePersenter<ResultBean> {
    void getData(String path);
}
