package com.fine.hug.user.ui;

import com.fine.hug.response.ApiResponse;
import com.fine.hug.user.application.UserService;
import com.fine.hug.user.application.dto.UserBasicCreateDto;
import com.fine.hug.user.infra.UserBasicTranslate;
import com.fine.hug.user.ui.dto.UserBasicResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation("회원가입")
    @PostMapping("/api/v1/createUser")
    public ApiResponse<UserBasicResponseDto> createUserBasic(@Valid @RequestBody UserBasicCreateDto createDto) {

        return ApiResponse.createOK(UserBasicTranslate.translate(userService.createUserBasic(createDto)));
    }

}
