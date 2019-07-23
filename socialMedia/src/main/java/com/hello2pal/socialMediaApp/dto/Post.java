package com.hello2pal.socialMediaApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Long postId;
    private String userId;
    private String content;
    private Date createdDate;
}

