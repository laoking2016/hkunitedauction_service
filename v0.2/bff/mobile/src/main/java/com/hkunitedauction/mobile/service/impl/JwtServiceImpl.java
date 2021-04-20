package com.hkunitedauction.mobile.service.impl;

import com.hkunitedauction.mobile.service.JwtService;
import com.hkunitedauction.util.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {

    @Autowired
    private JwtProvider jwtProvider;

    public String getUserNameFromToken(String token){

        token = token.replace("Bearer ", "");
        if(!jwtProvider.validateJwtToken(token)){
            return null;
        }
        return jwtProvider.getUserNameFromJwtToken(token);
    }
}
