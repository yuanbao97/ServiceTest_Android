package com.example.servicetest.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import androidx.annotation.Nullable;

import com.example.servicetest.util.LogUtil;

import java.io.File;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("name");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        LogUtil.e("MyIntentService", "Thread id is " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.e("MyIntentService", "onDestroy executed");
    }
}