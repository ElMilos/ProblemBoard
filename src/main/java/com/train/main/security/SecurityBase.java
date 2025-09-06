package com.train.main.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBase {

    @Bean
    PasswordEncoder passwordEncode()
    {return new BCryptPasswordEncoder();}

}
