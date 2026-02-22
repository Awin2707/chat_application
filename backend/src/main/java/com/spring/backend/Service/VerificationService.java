package com.spring.backend.Service;

import com.spring.backend.Entity.OtpEntity;
import com.spring.backend.Entity.UserEntity;
import com.spring.backend.Exception.UserException;
import com.spring.backend.Interface.VerificationInterface;
import com.spring.backend.Modal.OtpModal;
import com.spring.backend.Repo.OtpRepo;
import com.spring.backend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VerificationService implements VerificationInterface {

    @Autowired private OtpRepo otpRepo;
    @Autowired private UserRepo userRepo;

    @Override
    public UserEntity VerifyUser(OtpModal otpModal) {
        System.out.println("yes -1");
        OtpEntity otp = otpRepo.findByEmail(otpModal.getEmail());
        UserEntity user = userRepo.findByEmail(otpModal.getEmail());
            System.out.println(user.getEmail() + " , " + otp.getToken());
        if ((otp.getToken().isEmpty() || otp.getToken().equals("")) && user.isVerify()){
            throw new UserException("this otp have been used already !");
        }
        if (otp == null){
            throw new UserException("some think went wrong !");
        }else if (user != null){
            if (otp.getToken().equals(otpModal.getToken())){
                if (otp.getCode().equals(otpModal.getCode())){
                    if (otp.getLocalDateTime().isAfter( LocalDateTime.now())){
                        otp.setToken("");
                        user.setVerify(true);
                        otpRepo.save(otp);
                        userRepo.save(user);
                        return user;
                    }else {
                        throw new UserException("Otp have been expired !");
                    }
                }else {
                    throw new UserException("invalid OTP Code !");
                }
            }else {
                throw new UserException("some think went wrong !");
            }
        }
        return null;
    }
}
