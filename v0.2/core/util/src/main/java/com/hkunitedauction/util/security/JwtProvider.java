package com.hkunitedauction.util.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.stream.Collectors;

public class JwtProvider {
    //@Value("${member.jwt-secret}")
    private String jwtSecret;

    //@Value("${member.jwt-expiration}")
    private int jwtExpiration;

    public JwtProvider(String jwtSecret, int jwtExpiration){
        this.jwtSecret = jwtSecret;
        this.jwtExpiration = jwtExpiration;
    }

    public String generateJwtToken(Authentication authentication) {

        String[] roles =
                authentication.getAuthorities().stream().map(e -> e.getAuthority()).collect(Collectors.toList()).toArray(new String[0]);

        return Jwts.builder()
                .setSubject((authentication.getName()))
                .claim("role", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return this.getClaimsFromJwtToken(token).getSubject();
    }

    public Claims getClaimsFromJwtToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            //logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            //logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            //logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            //logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            //logger.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }
}
