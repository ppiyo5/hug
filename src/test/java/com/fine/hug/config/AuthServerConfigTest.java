package com.fine.hug.config;

import com.fine.hug.user.application.UserService;
import com.fine.hug.user.domain.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthServerConfigTest extends BaseControllerTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void getAuthToken() throws Exception {
        //given
        userRepository.save(UserBasic.builder()
                .userId("test")
                .password("1234")
                .userName("테스트")
                .email("test@test.com")
                .visitPath("검색")
                .role(Collections.singleton(Role.BASIC))
                .build());

        //when
        String clientId = "myApp";
        String clientSecret = "pass";
        this.mockMvc.perform(post("/oauth/token")
                .with(httpBasic(clientId, clientSecret))
                .param("username", createUser().getUserId())
                .param("password", createUser().getPassword())
                .param("grant_type", createUser().getPassword())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists());

        //then
    }

    public User createUser() {
        return userRepository.save(UserBasic.builder()
                .userId("test")
                .password("1234")
                .userName("테스트")
                .email("test@test.com")
                .visitPath("검색")
                .role(Collections.singleton(Role.BASIC))
                .build());
    }
}
