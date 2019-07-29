package com.hello2pal.socialMediaApp.controller;

import com.hello2pal.socialMediaApp.dto.Post;
import com.hello2pal.socialMediaApp.service.PostService;
import org.hamcrest.Matchers;
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

import java.util.Date;

import static com.hello2pal.socialMediaApp.util.ObjectMapperHelper.asJsonString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private PostService postService;

    @Test
    @DisplayName("Create Post - success")
    void testSucessfullPostCreation() throws Exception {
        Post postRequest = Post.builder().content("content").userId("U1001").build();
        Post postResponse = Post.builder().postId(1l).content("content").userId("U1001").createdDate(new Date()).build();
        doReturn(postResponse).when(postService).createPost(postRequest);

        mockMvc.perform(post("/api/v1.0/post")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(postRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.postId", Matchers.is(1)))
                .andExpect(jsonPath("$.userId", Matchers.is("U1001")));
        ;
    }
}