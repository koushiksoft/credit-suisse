package com.hello2pal.socialMediaApp.repository;

import com.hello2pal.socialMediaApp.entity.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PostRepository extends CrudRepository<PostEntity, Long> {
    List<PostEntity> findByUserIdIn(List<String> userIds, Pageable sortbyCreationdate);
    Long countByUserId(List<String> userIds);
}
