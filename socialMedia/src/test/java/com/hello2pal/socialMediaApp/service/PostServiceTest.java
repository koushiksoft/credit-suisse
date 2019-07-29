package com.hello2pal.socialMediaApp.service;

import com.hello2pal.socialMediaApp.dto.Post;
import com.hello2pal.socialMediaApp.entity.PostEntity;
import com.hello2pal.socialMediaApp.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PostServiceTest {
    @Autowired
    PostService postService;


    @MockBean
    PostRepository postRepository;

    @Test
    @DisplayName("Test post service - Get all posts ")
    public void testPostService() {
        Date d = new Date();
        Post post = Post.builder().createdDate(d).postId(1l).userId("U1001").content("hi1").build();
        Post post1 = Post.builder().createdDate(d).postId(2l).userId("U1001").content("hi2").build();
        Post post2 = Post.builder().createdDate(d).postId(3l).userId("U1001").content("hi3").build();


        PostEntity postEntity = new PostEntity();
        postEntity.setUserId("U1001");
        postEntity.setContent("hi1");
        postEntity.setCreatedDate(d);
        postEntity.setPostId(1l);


        PostEntity postEntity1 = new PostEntity();
        postEntity1.setUserId("U1001");
        postEntity1.setContent("hi2");
        postEntity1.setCreatedDate(d);
        postEntity1.setPostId(2l);

        PostEntity postEntity2 = new PostEntity();
        postEntity2.setUserId("U1001");
        postEntity2.setContent("hi3");
        postEntity2.setCreatedDate(d);
        postEntity2.setPostId(3l);


        doReturn(Arrays.asList(postEntity, postEntity1, postEntity2)).when(postRepository).findByUserIdIn(any(), any());

        List<Post> posts = postService.fetchAllPostsbyUsers(Arrays.asList("U1001"), 2, 0);

        assertEquals(posts.size(), 3);
        assertTrue(posts.contains(post));
        assertTrue(posts.contains(post1));
        assertTrue(posts.contains(post2));

    }

    @Test
    @DisplayName("Create Post - success")
    public void createPost() {
        Date d = new Date();
        Post post = Post.builder().createdDate(d).userId("U1001").content("hi1").build();
        Post returnExpectedpost = Post.builder().createdDate(d).postId(1l).userId("U1001").content("hi1").build();
        PostEntity postEntity = new PostEntity();
        postEntity.setUserId("U1001");
        postEntity.setContent("hi1");
        postEntity.setCreatedDate(d);
        postEntity.setPostId(1l);


        doReturn(postEntity).when(postRepository).save(any());

        Post returnVal = postService.createPost(post);

        assertEquals(returnVal.equals(returnExpectedpost), true);

    }


}
