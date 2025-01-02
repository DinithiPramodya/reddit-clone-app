package com.example.reddit_clone.service;

import com.example.reddit_clone.dto.PostRequest;
import com.example.reddit_clone.exceptions.SubredditNotFoundException;
import com.example.reddit_clone.mapper.PostMapper;
import com.example.reddit_clone.model.Post;
import com.example.reddit_clone.model.Subreddit;
import com.example.reddit_clone.model.User;
import com.example.reddit_clone.repository.PostRepository;
import com.example.reddit_clone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public Post save(PostRequest postRequest) {
        System.out.println("Inside service: save");
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        User currentUser = authService.getCurrentUser();
        postRepository.save(postMapper.map(postRequest, subreddit, currentUser));
        return postMapper.map(postRequest, subreddit, currentUser);
    }
}
