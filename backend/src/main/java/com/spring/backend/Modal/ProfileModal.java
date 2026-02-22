package com.spring.backend.Modal;

public class ProfileModal {

    private String token;

    private String name;

    private String path;

    public ProfileModal(String token, String name, String path) {
        this.token = token;
        this.name = name;
        this.path = path;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
