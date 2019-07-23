package com.hello2pal.socialMediaApp.service.Implementation;

import com.hello2pal.socialMediaApp.dto.Post;
import com.hello2pal.socialMediaApp.entity.PostEntity;
import com.hello2pal.socialMediaApp.repository.PostRepository;
import com.hello2pal.socialMediaApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.hello2pal.socialMediaApp.util.ModelMapperUtility.convertPostEntiyToPost;
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
        return convertPostEntiyToPost(returnPostEntity);
    }

    @Override
    public List<Post> fetchAllPostsByUsers(List<String> userIds, int limit, int offset) {
        return null;
    }
}
