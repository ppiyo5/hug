package com.fine.hug.user.ui;

import com.fine.hug.response.ApiResponse;
import com.fine.hug.user.application.UserService;
import com.fine.hug.user.application.dto.UserBasicCreateDto;
import com.fine.hug.user.application.dto.UserDoctorCreateDto;
import com.fine.hug.user.infra.UserBasicTranslate;
import com.fine.hug.user.infra.UserDoctorTranslate;
import com.fine.hug.user.ui.dto.UserResponseDto;
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
    public ApiResponse<UserResponseDto> createUserBasic(@ApiParam(value = "일반 회원가입 dto", required = true) @Valid @RequestBody UserBasicCreateDto createDto) {
        log.info("일반 회원가입 실행");
        log.info("createDto: " + createDto);
        return ApiResponse.createOK(UserBasicTranslate.translate(userService.createUserBasic(createDto)));
    }

    @ApiOperation("의료진 회원가입")
    @PostMapping("/createUserDoctor")
    public ApiResponse<UserResponseDto> createUserDoctor(@ApiParam(value = "의료진 회원등록 dto", required = true) @Valid @RequestBody UserDoctorCreateDto createDto) {
        log.info("의료진 회원등록 실행");
        log.info("createDto" + createDto);
        return ApiResponse.createOK(UserDoctorTranslate.translate(userService.createUserDoctor(createDto)));
    }

//    @ApiOperation("일반회원 로그인")
//    @PostMapping("/login")
//    public void loginUser(Model model, Authentication auth) {
//        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();
//        log.info("user controller에서 작성");
//        log.info("user::: " + principalDetails.getUser());
//        log.info("로그인 완료: principalDetails.getUser().getUserId(): " + principalDetails.getUser().getUserId()); // 로그인 정상적으로 되었다는 뜻
//        log.info("로그인 완료: principalDetails.getUser().getPassword(): " + principalDetails.getUser().getPassword());
//    }
}
