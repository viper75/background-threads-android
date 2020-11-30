package org.viper75.background_threads_android;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadsApplication extends Application {
    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(4);
    public static final Handler MAIN_THREAD_HANDLER = HandlerCompat.createAsync(Looper.getMainLooper());
}
