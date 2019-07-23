package com.hello2pal.socialMediaApp.service;

import com.hello2pal.socialMediaApp.dto.User;

public interface UserService {
    public User fetchUserById(String userId);
}
