package org.viper75.background_threads_android.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import org.viper75.background_threads_android.R;
import org.viper75.background_threads_android.databinding.LoginLayoutBinding;
import org.viper75.background_threads_android.helpers.LoginResponseParser;
import org.viper75.background_threads_android.helpers.Result;
import org.viper75.background_threads_android.repository.LoginRepository;
import org.viper75.background_threads_android.repository.response.LoginResponse;
import org.viper75.background_threads_android.viewmodels.LoginViewModel;
import org.viper75.background_threads_android.viewmodels.LoginViewModelFactory;

import static org.viper75.background_threads_android.ThreadsApplication.EXECUTOR_SERVICE;
import static org.viper75.background_threads_android.ThreadsApplication.MAIN_THREAD_HANDLER;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LoginLayoutBinding loginLayoutBinding = LoginLayoutBinding.inflate(getLayoutInflater());
        setContentView(loginLayoutBinding.getRoot());

        LoginRepository loginRepository = new LoginRepository(EXECUTOR_SERVICE, MAIN_THREAD_HANDLER);
        LoginViewModelFactory loginViewModelFactory = new LoginViewModelFactory(loginRepository);

        LoginViewModel loginViewModel = new ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel.class);
        loginViewModel.makeLoginRequest(11);
        loginViewModel.getLoginResponseMutableLiveData().observe(this, result -> {
            if (result instanceof Result.Success) {
                Result.Success<LoginResponse> responseSuccess = (Result.Success<LoginResponse>) result;
                loginLayoutBinding.loginResult.setText(responseSuccess.data.toString());
            } else {
                Result.Error<LoginResponse> error = (Result.Error<LoginResponse>) result;
                ((Result.Error<LoginResponse>) result).exception.printStackTrace();
                loginLayoutBinding.loginResult.setText("Login failed: " + error.exception.getMessage());
            }
        });
    }
}