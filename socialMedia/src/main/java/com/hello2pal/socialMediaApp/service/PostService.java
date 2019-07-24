package com.hello2pal.socialMediaApp.service;

import com.hello2pal.socialMediaApp.dto.Post;

import java.util.List;

public interface PostService {
    public Post createPost(Post post);
    public List<Post> fetchAllPostsbyUsers(List<String> userIds,int limit,int offset);
    public Long fetchAllPostsbyUsersCount(List<String> userIds);
}