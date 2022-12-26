package com.casestudy.shoppingcart.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class EncoderConfig {
    @Bean
    public PasswordEncoder passwordEncoder(String password){
        return new BCryptPasswordEncoder();
    }

//    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    public String encode(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("The password cannot be null or empty");
        }

        // generate a random salt
        String salt = BCrypt.gensalt();

        // hash the password with the salt
        String hashedPassword = BCrypt.hashpw(password, salt);

        // return the hashed password
        return hashedPassword;
    }
}
