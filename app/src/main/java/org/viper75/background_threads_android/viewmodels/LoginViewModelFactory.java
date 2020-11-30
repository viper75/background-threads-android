package org.viper75.background_threads_android.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.viper75.background_threads_android.repository.LoginRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private final LoginRepository loginRepository;

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(loginRepository);
        } else {
            throw new IllegalArgumentException("Unknown class");
        }
    }
}
