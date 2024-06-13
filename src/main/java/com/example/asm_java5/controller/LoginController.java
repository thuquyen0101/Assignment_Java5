package com.example.asm_java5.controller;

import com.example.asm_java5.service.KhachHangService;
import com.example.asm_java5.service.NhanVienService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

//    @Autowired
//    private AuthenticationManager authenticationManager;
    private final KhachHangService khService;
    private final NhanVienService nvService;

    @GetMapping("/login")
    public String formLogin() {
        return "login";
    }

    @GetMapping("/")
    public String test() {
        return "home";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

//    @PostMapping("/login-action")
//    public String login(@RequestParam String username, @RequestParam String password) {
//        try {
//            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password);
//            Authentication authentication = authenticationManager.authenticate(auth);
//            if(authentication.isAuthenticated()){
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//                return "redirect:/mau-sac/hien-thi";
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return "redirect:/nsx/hien-thi";
//    }

    public static void main(String[] args) {
        PasswordEncoder x = new BCryptPasswordEncoder();
        String pass = x.encode("123");
        System.out.println(pass);
    }
}
