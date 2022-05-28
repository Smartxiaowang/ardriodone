package com.example.db;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPollHelp {
    private static ThreadPoolExecutor te = null;

    public static ThreadPoolExecutor getPoll(){
        if (te == null) {
           te = (ThreadPoolExecutor) Executors.newCachedThreadPool();
            return te;
        }
        return te;
    }
}
