package com.hello2pal.socialMediaApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsFeedDTO {
    private Map<String,String> metaData;
    private List<Post> posts;
}