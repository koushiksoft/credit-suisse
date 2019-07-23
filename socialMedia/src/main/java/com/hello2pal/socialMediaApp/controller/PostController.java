package com.hello2pal.socialMediaApp.controller;

import com.hello2pal.socialMediaApp.dto.Post;
import com.hello2pal.socialMediaApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("post")
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        return ResponseEntity.ok(postService.createPost(post));
    }
}

