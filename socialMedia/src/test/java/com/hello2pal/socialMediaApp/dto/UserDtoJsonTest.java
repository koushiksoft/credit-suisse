package com.hello2pal.socialMediaApp.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@ExtendWith(SpringExtension.class)
public class UserDtoJsonTest {

    private static String USER_ID = "U1001";
    private static String USER_NAME = "MARRY";
    private static String GENDER = "FEMALE";
    private static final String JSON_TO_DESERIALIZE =
            "{\"userId\":\""
                    + USER_ID
                    + "\",\"userName\":\""
                    + USER_NAME
                    + "\",\"gender\":"
                    + "\"" + GENDER + "\""
                    + "}";
    @Autowired
    private JacksonTester<User> json;
    private User user;

    @BeforeEach
    public void setUp() {
        this.user = User.builder().userId(USER_ID).gender(GENDER).userName(USER_NAME).build();
    }

    @Test
    public void userIdSerializes() throws IOException {
        assertThat(this.json.write(user))
                .extractingJsonPathStringValue("@.userId")
                .isEqualTo(USER_ID);
    }

    @Test
    public void userIdDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getUserId()).isEqualTo(USER_ID);
    }

    @Test
    public void userNameSerializes() throws IOException {
        assertThat(this.json.write(user))
                .extractingJsonPathStringValue("@.userName")
                .isEqualTo(USER_NAME);
    }

    @Test
    public void userNameDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getUserName()).isEqualTo(USER_NAME);
    }

    @Test
    public void genderSerializes() throws IOException {
        assertThat(this.json.write(user))
                .extractingJsonPathStringValue("@.gender")
                .isEqualTo(GENDER);
    }

    @Test
    public void genderDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getGender()).isEqualTo(GENDER);
    }

}
