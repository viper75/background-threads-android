package org.viper75.background_threads_android;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadsApplication extends Application {
    ExecutorService executorService = Executors.newFixedThreadPool(4);
}
