package com.spl.dell.kaoshi.view.interfaces;

public interface BaseMainInterface<T> {

    void onSuccess(T t);
    void onError(Throwable throwable);

}
