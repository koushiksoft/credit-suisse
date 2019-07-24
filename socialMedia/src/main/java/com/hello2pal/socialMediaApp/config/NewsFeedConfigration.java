package com.hello2pal.socialMediaApp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("newsfeed")
@Data
public class NewsFeedConfigration {
    private String defaultLimit;
    private String defaultOffset;
}
