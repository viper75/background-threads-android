package org.viper75.background_threads_android.helpers;

public interface RepositoryCallback<T> {
    void onComplete(Result<T> result);
}
