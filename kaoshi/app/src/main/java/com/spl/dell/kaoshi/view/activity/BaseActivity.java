package com.spl.dell.kaoshi.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        initData();
    }

    abstract void initView();
    abstract void initData();
    abstract int getLayout();

    public <T>T find(int rID){
        return (T)findViewById(rID);

    }

}
