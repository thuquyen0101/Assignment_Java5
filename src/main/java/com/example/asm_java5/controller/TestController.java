package com.example.asm_java5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/hello")
public class TestController {
    @GetMapping("/test")
    public String testHello(){
        return "nhan-vien/index";
    }
}
