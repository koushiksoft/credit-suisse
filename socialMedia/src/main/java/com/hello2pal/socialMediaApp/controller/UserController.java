package com.hello2pal.socialMediaApp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0")
public class UserController {

    @GetMapping("/hello")
    public String hello(){

        return "Hello World!";
    }


}
