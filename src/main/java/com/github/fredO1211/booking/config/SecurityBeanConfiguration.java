package com.github.fredO1211.booking.config;

import com.github.fredO1211.booking.security.JwtParser;
import com.github.fredO1211.booking.security.SecurityContextUpdater;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityBeanConfiguration {

    @Bean
    public JwtParser jwtParser(){
        return new JwtParser();
    }

    @Bean
    public SecurityContextUpdater securityContextUpdater() {
        return new SecurityContextUpdater();
    }
}
