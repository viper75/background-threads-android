package org.viper75.background_threads_android.helpers;

import android.util.Log;

import org.viper75.background_threads_android.repository.response.LoginResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LoginResponseParser {
    public LoginResponse parse(InputStream inputStream) {
        Log.i(LoginResponseParser.class.getSimpleName(), "Parsing response.");

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            Log.i(LoginResponseParser.class.getSimpleName(), bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
