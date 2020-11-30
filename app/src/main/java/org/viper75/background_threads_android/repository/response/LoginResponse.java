package org.viper75.background_threads_android.repository.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResponse {
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
}
