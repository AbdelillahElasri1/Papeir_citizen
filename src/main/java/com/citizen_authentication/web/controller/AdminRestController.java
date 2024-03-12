package com.citizen_authentication.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRestController {
    @GetMapping("/hello")
    public String sayHello(){
        return "Welcome you are authenticated as Admin!\" ";
    }
}
