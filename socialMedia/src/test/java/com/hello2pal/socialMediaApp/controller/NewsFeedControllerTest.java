package com.hello2pal.socialMediaApp.controller;

import com.hello2pal.socialMediaApp.config.NewsFeedConfigration;
import com.hello2pal.socialMediaApp.dto.Post;
import com.hello2pal.socialMediaApp.service.PostService;
import com.hello2pal.socialMediaApp.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class NewsFeedControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    NewsFeedConfigration newsFeedConfigration;
    @MockBean
    private PostService postService;
    @MockBean
    private UserService userService;

    @BeforeEach
    public void setup() {

        doReturn("20").when(newsFeedConfigration).getDefaultLimit();
        doReturn("0").when(newsFeedConfigration).getDefaultOffset();
        doReturn(30L).when(postService).fetchAllPostsbyUsersCount(any());
    }

    @Test
    @DisplayName("Fetch users without limit and offset - success")
    void testFetchUserWithoutlimitandOffset() throws Exception {
        String followerId = "U1001";
        String following = "U1002";
        List<String> followedusers = new ArrayList<>(Collections.singletonList(following));
        doReturn(followedusers).when(userService).fetchAllFollowingUseIds(followerId);

        Post postByFollower;
        postByFollower = Post.builder().postId(1L).content("content1").userId(followerId).build();
        Post postByFollingUser = Post.builder().postId(2L).content("content2").userId(following).build();
        doReturn(Arrays.asList(postByFollower, postByFollingUser)).when(postService)
                .fetchAllPostsbyUsers(any(), anyInt(), anyInt());

        mockMvc.perform(get("/api/v1.0/user/U1001/newsfeed")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.metaData.totalcount", Matchers.is("30")))
                .andExpect(jsonPath("$.metaData.limit", Matchers.is("20")));

    }

    @Test
    @DisplayName("Fetch users without limit and offset - success")
    void testFetchUserWithlimitandOffset() throws Exception {
        String followerId = "U1001";
        String following = "U1002";
        List<String> followedusers = new ArrayList<>();
        doReturn(followedusers).when(userService).fetchAllFollowingUseIds(followerId);

        Post postByFollower = Post.builder().postId(1L).content("content1").userId(followerId).build();
        Post postByFollingUser = Post.builder().postId(2L).content("content2").userId(following).build();
        doReturn(Arrays.asList(postByFollower, postByFollingUser)).when(postService)
                .fetchAllPostsbyUsers(any(), anyInt(), anyInt());

        mockMvc.perform(get("/api/v1.0/user/U1001/newsfeed").param("limit", "10")
                .param("offset", "0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.metaData.totalcount", Matchers.is("30")))
                .andExpect(jsonPath("$.metaData.offset", Matchers.is("0")))
                .andExpect(jsonPath("$.metaData.limit", Matchers.is("10")))
                .andExpect(jsonPath("$.posts.length()", Matchers.is(2)));

    }

    @Test
    @DisplayName("Fetch users with Limit as Null- success")
    void testWhenLimitisNull() throws Exception {
        String followerId = "U1001";
        String following = "U1002";
        List<String> followedusers = new ArrayList<>();
        doReturn(followedusers).when(userService).fetchAllFollowingUseIds(followerId);

        Post postByFollower = Post.builder().postId(1L).content("content1").userId(followerId).build();
        Post postByFollingUser = Post.builder().postId(2L).content("content2").userId(following).build();
        doReturn(Arrays.asList(postByFollower, postByFollingUser)).when(postService)
                .fetchAllPostsbyUsers(any(), anyInt(), anyInt());

        mockMvc.perform(get("/api/v1.0/user/U1001/newsfeed")
                .param("offset", "0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.metaData.totalcount", Matchers.is("30")))
                .andExpect(jsonPath("$.metaData.offset", Matchers.is("0")))
                .andExpect(jsonPath("$.metaData.limit", Matchers.is("20")));

    }

    @Test
    @DisplayName("Fetch users with Offset as Null- success")
    void testWhenOffsetisNull() throws Exception {
        String followerId = "U1001";
        String following = "U1002";
        List<String> followedusers = new ArrayList<>();
        doReturn(followedusers).when(userService).fetchAllFollowingUseIds(followerId);

        Post postByFollower = Post.builder().postId(1L).content("content1").userId(followerId).build();
        Post postByFollingUser = Post.builder().postId(2L).content("content2").userId(following).build();
        doReturn(Arrays.asList(postByFollower, postByFollingUser)).when(postService)
                .fetchAllPostsbyUsers(any(), anyInt(), anyInt());

        mockMvc.perform(get("/api/v1.0/user/U1001/newsfeed").param("limit", "20")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.metaData.totalcount", Matchers.is("30")))
                .andExpect(jsonPath("$.metaData.offset", Matchers.is("0")))
                .andExpect(jsonPath("$.metaData.limit", Matchers.is("20")))
                .andExpect(jsonPath("$.posts.length()", Matchers.is(2)));

    }
}
