package com.hello2pal.socialMediaApp.repository;

import com.hello2pal.socialMediaApp.entity.PostEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    public void setUp(){
        PostEntity postEntity = new PostEntity();
        postEntity.setUserId("U4001");
        postEntity.setContent("Hi");
        postEntity.setCreatedDate(new Date());

        PostEntity postEntity1 = new PostEntity();
        postEntity1.setUserId("U4001");
        postEntity1.setContent("Hi1");
        postEntity1.setCreatedDate(new Date());

        PostEntity postEntity2 = new PostEntity();
        postEntity2.setUserId("U3001");
        postEntity2.setContent("Hi2");
        postEntity2.setCreatedDate(new Date());

        PostEntity postEntity3 = new PostEntity();
        postEntity3.setUserId("U3001");
        postEntity3.setContent("Hi3");
        postEntity3.setCreatedDate(new Date());

        entityManager.persist(postEntity);
        entityManager.persist(postEntity1);
        entityManager.persist(postEntity2);
        entityManager.persist(postEntity3);
        entityManager.flush();

    }

    @Test
    @DisplayName("Test pagable of Post - success")
    public void testPagablePost(){
        Pageable pageable = PageRequest.of(0,2);
        List<PostEntity> found = postRepository.findByUserIdIn(Arrays.asList("U4001","U3001"),pageable);
        assertEquals(found.size(),2);

    }


    @Test
    @DisplayName("Test count of Post - success")
    public void testPostCount(){
        long found = postRepository.countByUserIdIn(Arrays.asList("U4001","U3001"));
        assertEquals(found,4l);

    }

    @Test
    @DisplayName("Test Create Post - success")
    public void testCreatePost(){
        PostEntity postEntity = new PostEntity();
        postEntity.setUserId("U4001");
        postEntity.setContent("Hi");
        postEntity.setCreatedDate(new Date());

        PostEntity found = postRepository.save(postEntity);
        PostEntity fromEntityManger =  entityManager.find(PostEntity.class,found.getPostId());

        assertEquals(fromEntityManger,found);

    }
}
