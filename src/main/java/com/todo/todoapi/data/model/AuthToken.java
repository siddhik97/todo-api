package com.todo.todoapi.data.model;

public class AuthToken {
    private String token;
    private String userEmail;

    public AuthToken() {
    }

    public AuthToken(String token, String userEmail) {
        this.token = token;
        this.userEmail = userEmail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
