package com.hello2pal.socialMediaApp.controller;

import com.hello2pal.socialMediaApp.dto.SubscriptionDTO;
import com.hello2pal.socialMediaApp.exception.ExceptionCodes;
import com.hello2pal.socialMediaApp.exception.SocialAppException;
import com.hello2pal.socialMediaApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1.0")
public class SubscriptionController {


    @Autowired
    UserService userService;


    @PostMapping(path = "/subscriptions", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> followUser(@RequestBody SubscriptionDTO subscribeDTO) {

        subscriptionValidations(subscribeDTO);

        if (subscribeDTO.isFollowing()) {
            userService.follow(subscribeDTO.getFollowerID(), subscribeDTO.getFollowingID());
        } else {
            userService.unfollow(subscribeDTO.getFollowerID(), subscribeDTO.getFollowingID());
        }
        return ResponseEntity.ok().build();
    }

    private void subscriptionValidations(SubscriptionDTO subscribeDTO) {
        if (subscribeDTO.getFollowerID() == null ||
                subscribeDTO.getFollowingID() == null) {
            throw new SocialAppException(ExceptionCodes.VALIDATION_FAILED, "Following Id and Follower Id cannot be null");
        }

        if (subscribeDTO.getFollowingID().equalsIgnoreCase(subscribeDTO.getFollowerID())) {
            throw new SocialAppException(ExceptionCodes.VALIDATION_FAILED, "Following Id and Follower Id cannot be same");

        }

    }
}
