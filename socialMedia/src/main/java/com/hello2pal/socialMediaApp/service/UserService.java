package com.hello2pal.socialMediaApp.service;

import com.hello2pal.socialMediaApp.dto.User;

import java.util.List;

public interface UserService {
    User fetchUserById(String userid);

    List<String> fetchAllFollowingUseIds(String follwerdId);

    String follow(String followerId, String followeeId);

    String unfollow(String followerId, String followeeId);
}
