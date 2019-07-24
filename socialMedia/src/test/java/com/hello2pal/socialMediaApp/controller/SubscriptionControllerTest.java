package com.hello2pal.socialMediaApp.controller;

import com.hello2pal.socialMediaApp.dto.SubscriptionDTO;
import com.hello2pal.socialMediaApp.service.UserService;
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


import static com.hello2pal.socialMediaApp.util.ObjectMapperHelper.asJsonString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class SubscriptionControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Follow a follower - success")
    void testSucessfullFollow() throws Exception {
        String followerId = "U1001";
        String following = "U1002";
        doReturn("SUCESS").when(userService)
                .follow(followerId,following);
        SubscriptionDTO subscriptionDTO = SubscriptionDTO.builder().followerID(followerId).followingID(following).build();

        mockMvc.perform(post("/api/v1.0/subscriptions")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(subscriptionDTO)))
                .andExpect(status().isCreated());
    }
}