package com.spring.backend.Interface;

import com.spring.backend.Entity.UserEntity;
import com.spring.backend.Modal.OtpModal;

public interface VerificationInterface {

    UserEntity VerifyUser(OtpModal otpModal);
}
