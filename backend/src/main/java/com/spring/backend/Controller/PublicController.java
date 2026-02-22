package com.spring.backend.Controller;

import com.spring.backend.Entity.UserEntity;
import com.spring.backend.Exception.UserException;
import com.spring.backend.JwtToken.JwtToken;
import com.spring.backend.Modal.LoginModal;
import com.spring.backend.Modal.OtpModal;
import com.spring.backend.Modal.ProfileModal;
import com.spring.backend.Modal.RegisterModal;
import com.spring.backend.Service.LoginService;
import com.spring.backend.Service.UserService;
import com.spring.backend.Service.VerificationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/apiv1/public")
public class PublicController {

    @Autowired
    private UserService userService;
    @Autowired
    private VerificationService verificationService;
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private LoginService loginService;

    @GetMapping("/hello")
    public ResponseEntity<?> hello(){
        return ResponseEntity.ok().body("hello new user !");
    }

    @PostMapping("/register")
    public ResponseEntity<?> craeteAccount(@RequestBody RegisterModal registerModal){
        System.out.println("hello .. . .");
        try {
            System.out.println("calling . . .");
            String value = userService.createAccount(registerModal);
            if (!value.isEmpty() || !value.equals("")){
                return ResponseEntity.ok().body(value);
            }else {
                return ResponseEntity.badRequest().body("failed !");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addProfile")
    public ResponseEntity<?> craeteProfile(@RequestBody ProfileModal profile){
        try {
            String value = userService.createProfile(profile);
            if (!value.isEmpty() || !value.equals("")){
                return ResponseEntity.ok().body(value);
            }else {
                return ResponseEntity.badRequest().body("failed !");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody OtpModal OtpModal, HttpServletResponse response){
        try {
            System.out.println("yws-1");
            UserEntity user = verificationService.VerifyUser(OtpModal);
            System.out.println(user + "users");
            if (user != null){
                UsernamePasswordAuthenticationToken tokens = new UsernamePasswordAuthenticationToken(user.getEmail(), null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(tokens);
                String Token = jwtToken.generateToken(user.getEmail());
                Cookie cookie = new Cookie("_token", Token);
                cookie.setMaxAge(7 * 24 * 60 * 60);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
                System.out.println(Token + "tokens");
                return ResponseEntity.ok().body("success !" + Token);
            }else {
                return ResponseEntity.badRequest().body("failed !");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginModal loginModal, HttpServletResponse response){
        try {
            UserEntity user = loginService.loginAccount(loginModal);
            if (user != null){
                UsernamePasswordAuthenticationToken tokens = new UsernamePasswordAuthenticationToken(user.getEmail(), null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(tokens);
                String Token = jwtToken.generateToken(user.getEmail());
                Cookie cookie = new Cookie("_token", Token);
                cookie.setMaxAge(7 * 24 * 60 * 60);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
                System.out.println(Token + "tokens");
                return ResponseEntity.ok().body("success !" + Token);
            }else {
                throw new UserException("failed !");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
