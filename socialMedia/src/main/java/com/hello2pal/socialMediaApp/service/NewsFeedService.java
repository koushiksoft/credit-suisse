package com.hello2pal.socialMediaApp.service;

import com.hello2pal.socialMediaApp.dto.Post;

import java.util.List;

public interface NewsFeedService {

    public List<Post> fetchAllPostsByUsersAndFollowees(List<String> userIds, int limit, int offset);
}
