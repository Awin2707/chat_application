package com.spring.backend.Interface;

import com.spring.backend.Entity.UserEntity;
import com.spring.backend.Modal.LoginModal;

public interface LoginInterface {

    UserEntity loginAccount(LoginModal loginModal);
}
