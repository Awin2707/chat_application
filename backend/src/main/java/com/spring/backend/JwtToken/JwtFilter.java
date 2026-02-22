package com.spring.backend.JwtToken;

import com.spring.backend.Details.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired private JwtToken JwtToken;
    @Autowired private CustomUserDetails customUserDetails;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cokies = request.getCookies();
        Optional<Cookie> getCookie = Optional.empty();
        String jwtToken = null;
        if (cokies != null){
            getCookie = Arrays.stream(cokies).filter(v -> v.getName().equals("_token")).findFirst();
        }

        if (getCookie.isPresent()) {
            jwtToken = getCookie.get().getValue();
        }

        if (jwtToken != null && SecurityContextHolder.getContext().getAuthentication() == null){
            String userEmail = JwtToken.extractUserName(jwtToken);
            UserDetails ud = customUserDetails.loadUserByUsername(userEmail);
            if (ud != null){
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(ud, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        filterChain.doFilter(request,response);
    }
}
