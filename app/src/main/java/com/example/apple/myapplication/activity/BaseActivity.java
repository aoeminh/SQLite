package com.example.apple.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = getId();
        setContentView(id);
        intiView();
        setAction();

    }
    abstract int getId();
    abstract void intiView();
    abstract void  setAction();
}
