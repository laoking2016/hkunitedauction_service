package com.hkunitedauction.mobile.configuration;

import com.hkunitedauction.util.security.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguration {

    @Value("${security.jwt-secret}")
    private String jwtSecret;

    @Value("${security.jwt-expiration}")
    private int jwtExpiration;

    @Bean
    public JwtProvider jwtProvider(){
        return new JwtProvider(jwtSecret, jwtExpiration);
    }
}
