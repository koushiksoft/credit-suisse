package com.hello2pal.socialMediaApp.service;

import com.hello2pal.socialMediaApp.dto.User;
import com.hello2pal.socialMediaApp.entity.UserEntity;
import com.hello2pal.socialMediaApp.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Test
    @DisplayName("Test Fetch user - success")
    public void testFetchUser() {
        User user = User.builder().userId("U1001").userName("MIKE").gender("MALE").build();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId("U1001");
        userEntity.setUsernName("MIKE");
        userEntity.setGender("MALE");

        doReturn(Optional.of(userEntity)).when(userRepository).findById(any());

        User retUser = userService.fetchUserById("U1001");

        assertEquals(retUser, user);


    }

    @Test
    @DisplayName("Fetch all following users list")
    public void fetchAllUserFollowingLists() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId("U1001");

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUserId("U1002");

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUserId("U1003");

        Set<UserEntity> usersVal = new HashSet<>(Arrays.asList(userEntity2, userEntity1));
        userEntity.setFollowing(usersVal);

        doReturn(Optional.of(userEntity)).when(userRepository).findById(any());


        List<String> users = userService.fetchAllFollowingUseIds("U1001");

        System.out.println(users);

        assertTrue(users.contains("U1002"));
        assertTrue(users.contains("U1003"));
        assertEquals(users.size(), 2);

    }
}