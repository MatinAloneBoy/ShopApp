package com.example.myapplication.Application;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApplication extends Application {
    public static ExecutorService executorService = Executors.newFixedThreadPool(4);

}
