package com.hello2pal.socialMediaApp.util;

import com.hello2pal.socialMediaApp.dto.Post;
import com.hello2pal.socialMediaApp.dto.User;
import com.hello2pal.socialMediaApp.entity.PostEntity;
import com.hello2pal.socialMediaApp.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelMapperUtilityTest {

    @Test
    @DisplayName("Test UserEntity to UserDto")
    public void testUserEntityTOUserDtoConversion() {
        User user = User.builder().userName("MARRY").gender("FEMALE").userId("U1001").build();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsernName("MARRY");
        userEntity.setGender("FEMALE");
        userEntity.setUserId("U1001");

        User converted = ModelMapperUtility.convertUserFromUserEntity(userEntity);
        assertEquals(converted, user);
    }

    @Test
    @DisplayName("Test PostEntity to Post")
    public void testPostEntityTOPostDtoConversion() {
        Date currentDate = new Date();
        Post post = Post.builder().userId("U1001").content("HI").postId(1l).createdDate(currentDate).build();
        PostEntity postEntity = new PostEntity();
        postEntity.setCreatedDate(currentDate);
        postEntity.setPostId(1l);
        postEntity.setContent("HI");
        postEntity.setUserId("U1001");


        Post converted = ModelMapperUtility.convertPostEntityToPost(postEntity);
        assertEquals(converted, post);
    }


    @Test
    @DisplayName("Test Post to PostEntity")
    public void testPostTOPostEntityConversion() {
        Date currentDate = new Date();
        Post post = Post.builder().userId("U1001").content("HI").postId(1l).createdDate(currentDate).build();

        PostEntity postEntity = new PostEntity();
        postEntity.setCreatedDate(currentDate);
        postEntity.setPostId(1l);
        postEntity.setContent("HI");
        postEntity.setUserId("U1001");


        PostEntity converted = ModelMapperUtility.convertPostToPostEntity(post);
        assertEquals(postEntity, converted);
    }
}
