package com.hello2pal.socialMediaApp.controller;

import com.hello2pal.socialMediaApp.config.NewsFeedConfigration;
import com.hello2pal.socialMediaApp.dto.NewsFeedDTO;
import com.hello2pal.socialMediaApp.dto.Post;
import com.hello2pal.socialMediaApp.service.PostService;
import com.hello2pal.socialMediaApp.service.UserService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1.0")
public class NewsFeedController {


    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    NewsFeedConfigration newsFeedConfigration;

    @GetMapping("/user/{userid}/newsfeed")
    public ResponseEntity<NewsFeedDTO> fetchNewsFeedDTO(
            @PathVariable("userid") String userid,
            @RequestParam(value = "offset", required = false) String offset,
            @RequestParam(value = "limit", required = false) String limit) {

        List<String> allUserIds = userService.fetchAllFollowingUseIds(userid);
        allUserIds.add(userid);
        if (limit == null || offset == null) {
            limit = newsFeedConfigration.getDefaultLimit();
            offset = newsFeedConfigration.getDefaultOffset();
        }
        List<Post> allPosts = postService.fetchAllPostsbyUsers(allUserIds, Integer.parseInt(limit), Integer.parseInt(offset));
        Long postCount = postService.fetchAllPostsbyUsersCount(allUserIds);

        Map<String, String> metas = new HashMap<>();
        metas.put("limit", limit.toString());
        metas.put("offset", offset.toString());
        metas.put("totalcount", postCount.toString());
        metas.put("UUID", MDC.get("refId"));

        return ResponseEntity.ok().body(NewsFeedDTO.builder().metaData(metas).posts(allPosts).build());
    }
}