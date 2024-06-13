package com.example.asm_java5.security;


import com.example.asm_java5.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
//@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private AuthService service;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(AuthService authService) {
        //
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setUserDetailsService(authService);
        dap.setPasswordEncoder(passwordEncoder());
        return dap;
    }

    @Bean
    public HttpFirewall allowDoubleHttp() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return firewall;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(config -> config
                        .requestMatchers("/").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/login-action").permitAll()
                        .requestMatchers( "/ban-hang/**").hasRole("ADMIN")
                        .requestMatchers("/nsx/**").hasRole("USER")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                                .loginPage("/login")
                                .successHandler(new AuthenticationSuccessHandle())
                                .permitAll()
                )
                .logout(logout -> logout.permitAll()
                )
                .exceptionHandling(configure -> configure.accessDeniedPage("/error"))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .csrf(AbstractHttpConfigurer::disable)
                .requestCache(requestCache -> requestCache.disable())
        ;
        http.setSharedObject(HttpFirewall.class, allowDoubleHttp());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return service;
    }

}