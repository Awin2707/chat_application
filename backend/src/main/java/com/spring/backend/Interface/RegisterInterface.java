package com.spring.backend.Interface;

import com.spring.backend.Modal.ProfileModal;
import com.spring.backend.Modal.RegisterModal;

public interface RegisterInterface {

    String createAccount(RegisterModal registerModal);

    String createProfile(ProfileModal profileModal);
}
