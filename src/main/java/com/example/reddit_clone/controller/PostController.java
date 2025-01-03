package com.example.reddit_clone.controller;

import com.example.reddit_clone.dto.PostRequest;
import com.example.reddit_clone.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor

public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostRequest postRequest) {
        System.out.println("Inside controller");
        postService.save(postRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
