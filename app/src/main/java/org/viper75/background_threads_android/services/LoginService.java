package org.viper75.background_threads_android.services;

import org.viper75.background_threads_android.repository.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LoginService {
    @GET("users/{userId}")
    Call<LoginResponse> login(@Path("userId") int userId);
}
