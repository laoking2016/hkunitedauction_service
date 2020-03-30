package com.hkunitedauction.member.security;

import com.hkunitedauction.util.security.JwtAuthEntryPoint;
import com.hkunitedauction.util.security.JwtAuthTokenFilter;
import com.hkunitedauction.util.security.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.jwt-secret}")
    private String jwtSecret;

    @Value("${security.jwt-expiration}")
    private int jwtExpiration;

    @Value("security.password-secret")
    private String passwordSecret;

    @Bean
    public JwtAuthEntryPoint unauthorizedHandler(){
        return new JwtAuthEntryPoint();
    }

    @Bean
    public JwtProvider jwtProvider(){
        return new JwtProvider(jwtSecret, jwtExpiration);
    }

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter(jwtProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder(this.passwordSecret);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeRequests()
                //.antMatchers("/authenticate").permitAll()
                //.antMatchers("/file").permitAll()
                //.antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}