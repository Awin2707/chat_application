package com.spring.backend.Service;

import com.spring.backend.Interface.TokenInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Token implements TokenInterface {

    @Value("${spring.token}")
    private String uniqueToken;

    @Override
    public String generateUniqueToken() {
        String token = "";
        Random rand = new Random();
        for (byte i = 0; i < 7; i++) {
            token += uniqueToken.charAt(rand.nextInt(uniqueToken.length()));
        }
        rand = null;
        return token;
    }

    @Override
    public String generateOtpCode() {
        String otp = "1234567890";
        String Code = "";
        Random rand = new Random();
        for (byte i = 0; i < 6; i++) {
            Code += otp.charAt(rand.nextInt(otp.length()));
        }
        rand = null;
        return Code;
    }
}
