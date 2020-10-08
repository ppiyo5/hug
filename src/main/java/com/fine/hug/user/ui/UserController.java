package com.fine.hug.user.ui;

import com.fine.hug.user.application.UserService;
import com.fine.hug.user.application.dto.UserCreateDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation("회원가입")
    @PostMapping("/api/v1/createUser")
    public Long save(@RequestBody UserCreateDto dto) {
        return userService.save(dto);
    }

}
