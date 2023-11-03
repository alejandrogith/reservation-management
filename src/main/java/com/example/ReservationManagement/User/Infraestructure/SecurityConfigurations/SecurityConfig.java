package com.example.ReservationManagement.User.Infraestructure.SecurityConfigurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {


    private  final  CustomUserDetailsService _customUserDetailsService;

    private  final  JwtAutenticationFilter  _jwtAutenticationFilter;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtAutenticationFilter jwtAutenticationFilter) {
        this._customUserDetailsService = customUserDetailsService;
        this._jwtAutenticationFilter = jwtAutenticationFilter;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {




        return httpSecurity
                .csrf(config -> config.disable())
                .authorizeHttpRequests(request -> request.requestMatchers("/doc/**","/v3/api-docs/**","api/user/login","api/user/register")
                        .permitAll().anyRequest().authenticated())
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilterBefore(_jwtAutenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(x->x.authenticationEntryPoint(new JwtAuthenticationEntryPoint()))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(_customUserDetailsService)
                .passwordEncoder(passwordEncoder)
                .and().build();
    }


}
