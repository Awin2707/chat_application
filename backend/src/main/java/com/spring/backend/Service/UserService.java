package com.spring.backend.Service;

import com.spring.backend.Entity.OtpEntity;
import com.spring.backend.Entity.UserEntity;
import com.spring.backend.Exception.UserException;
import com.spring.backend.Interface.RegisterInterface;
import com.spring.backend.Modal.ProfileModal;
import com.spring.backend.Modal.RegisterModal;
import com.spring.backend.Repo.OtpRepo;
import com.spring.backend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService implements RegisterInterface {

    @Autowired private UserRepo userRepo;
    @Autowired private BCryptPasswordEncoder passwordEncoder;
    @Autowired private Token token;
    @Autowired private OtpRepo otpRepo;
    @Autowired private MailService mailService;

    @Override
    public String createAccount(RegisterModal registerModal) {
        UserEntity user = userRepo.findByEmail(registerModal.getEmail());
        if (user != null && user.isVerify()){
            throw new UserException("this email id already exists !");
        }else if (user != null && !user.isVerify()){
            String Token = token.generateUniqueToken();
            UserEntity users = new UserEntity(user.getId(), registerModal.getEmail(), registerModal.getPhone(),passwordEncoder.encode(registerModal.getPass()), null, null, false, Token);
            userRepo.save(users);
            return Token;
        }else {
            String Token = token.generateUniqueToken();
            UserEntity users = new UserEntity(null, registerModal.getEmail(), registerModal.getPhone(),passwordEncoder.encode(registerModal.getPass()), null, null, false, Token);
            userRepo.save(users);
            return Token;
        }
    }

    @Override
    public String createProfile(ProfileModal profileModal) {
        UserEntity user = userRepo.findByToken(profileModal.getToken());
        if (user != null){
            user.setToken("");
            user.setName(profileModal.getName());
            user.setProfile(profileModal.getPath());
            userRepo.save(user);
            String Token = token.generateUniqueToken();
            String OTP = token.generateOtpCode();
            OtpEntity otps = otpRepo.findByEmail(user.getEmail());
            if (otps != null){
                OtpEntity otp = new OtpEntity(otps.getId(), Token, user.getEmail(), OTP, LocalDateTime.now().plusMinutes(5));
                mailService.SendOtpMail(user, OTP);
                otpRepo.save(otp);
            }else {
                OtpEntity otp = new OtpEntity(null, Token, user.getEmail(), OTP, LocalDateTime.now().plusMinutes(5));
                mailService.SendOtpMail(user, OTP);
                otpRepo.save(otp);
            }
            return Token;
        }else {
            throw new UserException("some think went wrong please refresh page");
        }
    }
}
