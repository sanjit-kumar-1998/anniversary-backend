package com.anniversary.backend.model;

public class UnlockRequest {
    private String password;

    public UnlockRequest() {}

    public UnlockRequest(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}