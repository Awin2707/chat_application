package com.spring.backend.Interface;

import com.spring.backend.Entity.UserEntity;

public interface MailInterface {
    void SendOtpMail(UserEntity user, String Code);
}
