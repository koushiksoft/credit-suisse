package com.hello2pal.socialMediaApp.service.implementation;

import com.hello2pal.socialMediaApp.dto.User;
import com.hello2pal.socialMediaApp.entity.UserEntity;
import com.hello2pal.socialMediaApp.exception.ExceptionCodes;
import com.hello2pal.socialMediaApp.exception.SocialAppException;
import com.hello2pal.socialMediaApp.repository.UserRepository;
import com.hello2pal.socialMediaApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hello2pal.socialMediaApp.util.ModelMapperUtility.convertUserFromUserEntity;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User fetchUserById(String userId) {
        UserEntity userEntity = validateUserId(userId);
        return convertUserFromUserEntity(userEntity);
    }


    private UserEntity validateUserId(String userID) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userID);
        userEntityOptional.orElseThrow(() ->
                new SocialAppException(ExceptionCodes.USER_ID_NOT_FOUND, "No user found with userid" + userID));
        return userEntityOptional.get();
    }

    @Override
    public List<String> fetchAllFollowingUseIds(String follwerdId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(follwerdId);
        List<String> allFollwingIds =
                userEntityOptional.get().getFollowing().stream()
                        .map(entity -> entity.getUserId()).collect(Collectors.toList());
        return allFollwingIds;
    }


    @Override
    public String follow(String followerId, String followeeId) {
        UserEntity follwerUserEntity = validateUserId(followerId);
        UserEntity followeeUserEntiry = validateUserId(followeeId);

        if (follwerUserEntity.getFollowing() != null && follwerUserEntity.getFollowing().contains(followeeUserEntiry)) {
            throw new SocialAppException(ExceptionCodes.VALIDATION_FAILED, "Unable to Subscriber . ,User id " + followerId + " already follows " + followeeId);
        }

        followeeUserEntiry.addFollower(follwerUserEntity);
        userRepository.save(followeeUserEntiry);
        return "SUCESS";
    }

    @Override
    public String unfollow(String followerId, String followeeId) {
        UserEntity follwerUserEntity = validateUserId(followerId);
        UserEntity followeeUserEntiry = validateUserId(followeeId);

        if (follwerUserEntity.getFollowing() != null && !follwerUserEntity.getFollowing().contains(followeeUserEntiry)) {
            throw new SocialAppException(ExceptionCodes.VALIDATION_FAILED, "Unable to unscubcribe ,User id " + followerId + " doesnot follow " + followeeId);
        }
        followeeUserEntiry.removeFollower(follwerUserEntity);
        userRepository.save(followeeUserEntiry);
        return "SUCESS";
    }


}
