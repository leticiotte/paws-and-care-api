package com.example.pawsAndCare.domain.model.response;

public class SigninResponse {
    private String token;
    private String adminId;

    public SigninResponse() {
    }

    public SigninResponse(String token, String adminId) {
        this.token = token;
        this.adminId = adminId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
