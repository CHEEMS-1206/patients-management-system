package com.sentysingh.auth_service.dto;

public class LoginResponseDTO {
    final private String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
