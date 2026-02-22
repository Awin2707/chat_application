package com.spring.backend.Service;

import com.spring.backend.Entity.UserEntity;
import com.spring.backend.Interface.MailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService implements MailInterface {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void SendOtpMail(UserEntity user, String Code) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Your OTP Code is : " + Code.charAt(0) + "XXXXX");
        simpleMailMessage.setText(
                "Hello User [" + user.getName() + "] \n Your verification code is: " + Code + "\n This code is valid for 5 minutes. \n  Please do not share this code with anyone. \n If you did not request this code, please ignore this email. \n Thank you,\n The Support Team"
        );
        javaMailSender.send(simpleMailMessage);
    }
}
