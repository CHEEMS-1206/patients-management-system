package com.sentysingh.auth_service.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequestDTO {
    @NotBlank(message = "Email filed can not be blank !")
    @Email(message = "Please Enter a valid email")
    private String email;

    @NotBlank(message = "Password filed can not be blank !")
    @Size(min = 8, message = "Password must be of min 8 chars in length !")
    @Size(max = 16, message = "Password can not be of more than 16 chars in length")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
