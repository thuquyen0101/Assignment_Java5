package com.example.asm_java5;

import jakarta.servlet.DispatcherType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
//@Controller
public class AsmJava5Application {

    public static void main(String[] args) {
        SpringApplication.run(AsmJava5Application.class, args);
    }
//    @Bean
//    public SecurityFilterChain config(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((auth) -> auth
//                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//                        .requestMatchers("/", "/view/**").permitAll()
//                        .anyRequest().authenticated()
//                );
//
//        return http.build();
//    }


//    @RequestMapping("/")
//    public String home() {
//        return "login";
//    }
}
