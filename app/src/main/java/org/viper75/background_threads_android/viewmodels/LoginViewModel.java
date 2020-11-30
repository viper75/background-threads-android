package org.viper75.background_threads_android.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.viper75.background_threads_android.helpers.Result;
import org.viper75.background_threads_android.repository.LoginRepository;
import org.viper75.background_threads_android.repository.response.LoginResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class LoginViewModel extends ViewModel {

    private final LoginRepository loginRepository;

    @Getter
    private MutableLiveData<Result<LoginResponse>> loginResponseMutableLiveData = new MutableLiveData<>();

    public LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public void makeLoginRequest(int userId) {
        loginRepository.makeLoginRequest(userId, result -> {
            loginResponseMutableLiveData.setValue(result);
        });
    }
}
