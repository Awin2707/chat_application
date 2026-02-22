package com.spring.backend.Modal;

public class RegisterModal {

    private String email;
    private String phone;
    private String pass;

    public RegisterModal(String email, String phone, String pass) {
        this.email = email;
        this.phone = phone;
        this.pass = pass;
    }

    public RegisterModal() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
