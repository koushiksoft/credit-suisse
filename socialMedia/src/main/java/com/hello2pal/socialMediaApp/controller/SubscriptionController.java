package com.hello2pal.socialMediaApp.controller;

import com.hello2pal.socialMediaApp.dto.SubscriptionDTO;
import com.hello2pal.socialMediaApp.exception.ExceptionCodes;
import com.hello2pal.socialMediaApp.exception.SocialAppException;
import com.hello2pal.socialMediaApp.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1.0")
public class SubscriptionController {


    @Autowired
    UserService userService;

    @ApiOperation(value = "Follow -> Follower follows a followee")
    @PostMapping(path = "/subscriptions", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> followUser(@RequestBody SubscriptionDTO subscribeDTO) {
        subscriptionValidations(subscribeDTO);
        userService.follow(subscribeDTO.getFollowerID(), subscribeDTO.getFollowingID());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "Unfollow -> Follower unfollows a followee")
    @DeleteMapping(path = "/subscriptions", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> unfollowUser(@RequestBody SubscriptionDTO subscribeDTO) {
        subscriptionValidations(subscribeDTO);
        userService.unfollow(subscribeDTO.getFollowerID(), subscribeDTO.getFollowingID());
        return ResponseEntity.ok().build();
    }

    private void subscriptionValidations(SubscriptionDTO subscribeDTO) {
        if (subscribeDTO.getFollowerID() == null ||
                subscribeDTO.getFollowingID() == null) {
            throw new SocialAppException(ExceptionCodes.VALIDATION_FAILED, "Follwing Id and Follwer Id cannot be null");
        }

        if (subscribeDTO.getFollowingID().equalsIgnoreCase(subscribeDTO.getFollowerID())) {
            throw new SocialAppException(ExceptionCodes.VALIDATION_FAILED, "Follwing Id and Follwer Id cannot be same");

        }

    }
}
