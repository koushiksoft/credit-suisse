package com.hello2pal.socialMediaApp.repository;

import com.hello2pal.socialMediaApp.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {
}
