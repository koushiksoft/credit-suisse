package com.hello2pal.socialMediaApp.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Post {
    private Long postId;
    private String userId;
    private String content;
    private Date createdDate;
}


