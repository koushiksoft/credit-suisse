package com.hello2pal.socialMediaApp.service;

import com.hello2pal.socialMediaApp.dto.User;

import java.util.List;

public interface UserService {
    public User fetchUserById(String userId);
    public List<String> fetchAllFollowingUseIds(String follwerdId);
    public String follow(String followerId , String followeeId);
    public String unfollow(String followerId , String followeeId);
}
