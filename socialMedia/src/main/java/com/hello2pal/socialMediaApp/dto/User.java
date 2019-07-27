package com.hello2pal.socialMediaApp.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class User {
    private String userId;
    private String userName;
    private String gender;
}