package com.spring.backend.Service;

import com.spring.backend.Entity.UserEntity;
import com.spring.backend.Exception.UserException;
import com.spring.backend.Interface.LoginInterface;
import com.spring.backend.Modal.LoginModal;
import com.spring.backend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginInterface {

    @Autowired protected UserRepo userRepo;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity loginAccount(LoginModal loginModal) {
        UserEntity user = userRepo.findByEmail(loginModal.getEmail());
        if (user != null && passwordEncoder.matches(loginModal.getPass(), user.getPass())){
            return user;
        }else {
            throw new UserException("invalid user email id or password !");
        }
    }
}
