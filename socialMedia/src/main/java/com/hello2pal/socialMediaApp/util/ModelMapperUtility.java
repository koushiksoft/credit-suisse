package com.hello2pal.socialMediaApp.util;

import com.hello2pal.socialMediaApp.dto.Post;
import com.hello2pal.socialMediaApp.dto.User;
import com.hello2pal.socialMediaApp.entity.PostEntity;
import com.hello2pal.socialMediaApp.entity.UserEntity;
import org.modelmapper.ModelMapper;

public class ModelMapperUtility {

    private static ModelMapper modelMapper = new ModelMapper();

    public static User convertUserFromUserEntity(UserEntity userEntity){
        return  modelMapper.map(userEntity, User.class);
    }

    public static PostEntity convertPostToPostEntity(Post post){
        return modelMapper.map(post , PostEntity.class);
    }

    public static Post convertPostEntiyToPost(PostEntity postEntity){
        return modelMapper.map(postEntity , Post.class);
    }
}
