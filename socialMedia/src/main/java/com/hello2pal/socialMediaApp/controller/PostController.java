package com.hello2pal.socialMediaApp.controller;

import com.hello2pal.socialMediaApp.dto.Post;
import com.hello2pal.socialMediaApp.service.PostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ApiOperation(value = "create a post")
    @PostMapping("post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) throws Exception {
        Post postResponse = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponse);
    }
}