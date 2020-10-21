package com.fine.hug.user.ui;

import com.fine.hug.response.ApiResponse;
import com.fine.hug.user.application.UserService;
import com.fine.hug.user.application.dto.UserBasicCreateDto;
import com.fine.hug.user.application.dto.UserDoctorCreateDto;
import com.fine.hug.user.infra.UserBasicTranslate;
import com.fine.hug.user.infra.UserDoctorTranslate;
import com.fine.hug.user.ui.dto.UserBasicResponseDto;
import com.fine.hug.user.ui.dto.UserDoctorResponseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Log
public class UserController {

    private final UserService userService;

    @ApiOperation("일반 회원가입")
    @PostMapping("/createUserBasic")
    public ApiResponse<UserBasicResponseDto> createUserBasic(@ApiParam(value = "일반 회원가입 dto", required = true) @Valid @RequestBody UserBasicCreateDto createDto) {
        log.info("일반 회원가입 실행");
        log.info("createDto: " + createDto);
        return ApiResponse.createOK(UserBasicTranslate.translate(userService.createUserBasic(createDto)));
    }

    @ApiOperation("의료진 회원가입")
    @PostMapping("/createUserDoctor")
    public ApiResponse<UserDoctorResponseDto> createUserDoctor(@ApiParam(value = "의료진 회원등록 dto", required = true) @Valid @RequestBody UserDoctorCreateDto createDto) {
        log.info("의료진 회원등록 실행");
        log.info("createDto" + createDto);
        return ApiResponse.createOK(UserDoctorTranslate.translate(userService.createUserDoctor(createDto)));
    }
}
