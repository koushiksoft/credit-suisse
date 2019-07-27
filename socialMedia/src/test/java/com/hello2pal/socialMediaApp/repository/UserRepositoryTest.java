package com.hello2pal.socialMediaApp.repository;

import com.hello2pal.socialMediaApp.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Add Users to repo - success")
    public void testCreateUser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setGender("MALE");
        userEntity.setUserId("U4001");
        userEntity.setUsernName("Hi User");

        entityManager.persist(userEntity);
        entityManager.flush();

        UserEntity found = userRepository.findById(userEntity.getUserId()).get();

        assertEquals(found.getUsernName(),userEntity.getUsernName());

    }

    @Test
    @DisplayName("Add Users with following - success")
    public void testUserFollowing(){
        UserEntity following = new UserEntity();
        following.setGender("MALE");
        following.setUserId("U4002");
        following.setUsernName("User1");

        UserEntity follower = new UserEntity();
        follower.setGender("MALE");
        follower.setUserId("U4001");
        follower.setUsernName("User2");

        entityManager.persist(following);
        entityManager.persist(follower);
        entityManager.flush();

        following =entityManager.find(UserEntity.class,following.getUserId());
        following.addFollower(follower);

        entityManager.persist(following);
        entityManager.flush();

        UserEntity found = userRepository.findById(follower.getUserId()).get();

        assertEquals(found.getFollowing().size(),1);
        assertEquals(found.getFollowing().contains(following),true);

    }
}
