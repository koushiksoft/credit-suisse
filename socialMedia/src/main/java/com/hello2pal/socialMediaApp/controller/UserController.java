package com.hello2pal.socialMediaApp.controller;


import com.hello2pal.socialMediaApp.dto.User;
import com.hello2pal.socialMediaApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> fetchUser(@PathVariable("id") String userID){
        return ResponseEntity.ok().body(userService.fetchUserById(userID));
    }


}
