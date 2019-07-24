package com.hello2pal.socialMediaApp.service;

import com.hello2pal.socialMediaApp.dto.Post;

import java.util.List;

public interface PostService {
    Post createPost(Post post);

    List<Post> fetchAllPostsbyUsers(List<String> userIds, int limit, int offset);

    Long fetchAllPostsbyUsersCount(List<String> userIds);
}
