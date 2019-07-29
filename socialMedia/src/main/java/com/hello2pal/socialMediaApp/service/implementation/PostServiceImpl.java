package com.hello2pal.socialMediaApp.service.implementation;

import com.hello2pal.socialMediaApp.dto.Post;
import com.hello2pal.socialMediaApp.entity.PostEntity;
import com.hello2pal.socialMediaApp.repository.PostRepository;
import com.hello2pal.socialMediaApp.service.PostService;
import com.hello2pal.socialMediaApp.util.ModelMapperUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.hello2pal.socialMediaApp.util.ModelMapperUtility.convertPostEntityToPost;
import static com.hello2pal.socialMediaApp.util.ModelMapperUtility.convertPostToPostEntity;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;


    @Override
    public Post createPost(Post post) {
        PostEntity postEntity = convertPostToPostEntity(post);
        postEntity.setCreatedDate(new Date());
        PostEntity returnPostEntity = postRepository.save(postEntity);
        return convertPostEntityToPost(returnPostEntity);
    }

    @Override
    public List<Post> fetchAllPostsbyUsers(List<String> userIds, int limit, int offset) {
        Pageable sortedByDate =
                PageRequest.of(offset, limit, Sort.by("createdDate"));
        List<PostEntity> postEntityList = postRepository.findByUserIdIn(userIds, sortedByDate);
        return postEntityList.stream().map(ModelMapperUtility::convertPostEntityToPost).collect(Collectors.toList());
    }

    @Override
    public Long fetchAllPostsbyUsersCount(List<String> userIds) {
        return postRepository.countByUserIdIn(userIds);
    }
}