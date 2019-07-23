package com.hello2pal.socialMediaApp.repository;

import com.hello2pal.socialMediaApp.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}