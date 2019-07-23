package com.hello2pal.socialMediaApp.service.Implementation;

import com.hello2pal.socialMediaApp.dto.User;
import com.hello2pal.socialMediaApp.entity.UserEntity;
import com.hello2pal.socialMediaApp.exception.ExceptionCodes;
import com.hello2pal.socialMediaApp.exception.SocialAppException;
import com.hello2pal.socialMediaApp.repository.UserRepository;
import com.hello2pal.socialMediaApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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



    private UserEntity validateUserId(String userID){
        Optional<UserEntity> userEntityOptional = userRepository.findById(userID);
        userEntityOptional.orElseThrow(() ->
                new SocialAppException(ExceptionCodes.USER_ID_NOT_FOUND,"No user found with userid" + userID));
        return userEntityOptional.get();
    }



}
