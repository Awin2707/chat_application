package com.spring.backend.Security;

import com.spring.backend.Details.CustomUserDetails;
import com.spring.backend.JwtToken.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Autowired
    JwtFilter jwtFilter;
    @Autowired private CustomUserDetails customUserDetails;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        httpSecurity.csrf((csrf) -> csrf.disable()
        ).authorizeHttpRequests((auth) -> auth.requestMatchers("/apiv1/public/**").permitAll().anyRequest().authenticated()
            ).formLogin((form) -> form.disable()
            ).cors((cors) -> cors.configurationSource(configurationSource())
        ).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class
        ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource configurationSource(){
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowedMethods(List.of("GET","PUT","POST","DELETE"));
        cors.setAllowedOrigins(List.of("http://localhost:3000/"));
        cors.setAllowCredentials(true);
        cors.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource url = new UrlBasedCorsConfigurationSource();
        url.registerCorsConfiguration("/**", cors);
        return url;
    }

    @Bean
    public DaoAuthenticationProvider provider(){
        DaoAuthenticationProvider configurer = new DaoAuthenticationProvider(customUserDetails);
        configurer.setPasswordEncoder(passwordEncoder());
        return configurer;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
