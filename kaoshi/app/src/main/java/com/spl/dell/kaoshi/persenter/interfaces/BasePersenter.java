package com.spl.dell.kaoshi.persenter.interfaces;

public interface BasePersenter<T> {

    void onSuccess(T t);
    void onError(Throwable throwable);

}
