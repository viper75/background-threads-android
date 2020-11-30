package org.viper75.background_threads_android.repository;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import org.viper75.background_threads_android.helpers.LoginResponseParser;
import org.viper75.background_threads_android.helpers.RepositoryCallback;
import org.viper75.background_threads_android.helpers.Result;
import org.viper75.background_threads_android.repository.response.LoginResponse;
import org.viper75.background_threads_android.services.LoginService;
import org.viper75.background_threads_android.utils.RetrofitConfig;

import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Executor;

import javax.net.ssl.HttpsURLConnection;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AllArgsConstructor
public class LoginRepository {

    private final Executor executor;
    private final Handler handler;

    public void makeLoginRequest(final int userId, final RepositoryCallback<LoginResponse> callback) {
        executor.execute(() -> {
            makeSynchronousLoginRequest(userId, callback);
        });
    }

    public void makeSynchronousLoginRequest(int userId, RepositoryCallback<LoginResponse> callback) {

        LoginService loginService = RetrofitConfig.getInstance().create(LoginService.class);

        loginService.login(userId).enqueue(new Callback<LoginResponse>() {
            @SneakyThrows
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call,
                                   @NonNull Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    notifyResult(new Result.Success<>(response.body()), callback);
                } else {
                    assert response.errorBody() != null;
                    notifyResult(new Result.Error<>(new Exception(response.errorBody().string())), callback);
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                notifyResult(new Result.Error<>((Exception) t), callback);
            }
        });
    }

    private void notifyResult(Result<LoginResponse> result, RepositoryCallback<LoginResponse> callback) {
        handler.post(() -> {
            callback.onComplete(result);
        });
    }
}
