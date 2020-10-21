package com.fine.hug.user.application;

import com.fine.hug.user.domain.Role;
import com.fine.hug.user.domain.UserBasic;
import com.fine.hug.user.domain.UserRepository;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Logger
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository UserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

//    @Test
//    public void 유저_있는지_조회() {
//        //given
//        UserBasic userBasic = createUserBasic();
//
//        //when
//        UserBasic resultUser = userService.findByUserId("user");
//
//        //then
//        assertThat(resultUser.getUserId()).isEqualTo(userBasic);
//    }

    @Test(expected = UsernameNotFoundException.class)
    public void 유저_아이디가_없을때() {
        String userId = "test2";
        userService.loadUserByUsername(userId);
    }

    @Test
    public void 유저_아이디가_있을때() {
        String userId = "test";
        userService.loadUserByUsername(userId);
    }

    public UserBasic createUserBasic() {
        return UserRepository.save(UserBasic.builder()
                .userId("user")
                .password("1234")
                .userName("테스트")
                .email("test@test.com")
                .visitPath("인터넷 검색")
                .role(Collections.singleton(Role.BASIC))
                .build());


    }

}
