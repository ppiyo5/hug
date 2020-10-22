package com.fine.hug.user.application;

import com.fine.hug.error.ErrorCode;
import com.fine.hug.exception.AlreadyIdException;
import com.fine.hug.exception.BusinessException;
import com.fine.hug.exception.NotFoundException;
import com.fine.hug.user.application.dto.UserBasicCreateDto;
import com.fine.hug.user.application.dto.UserDoctorCreateDto;
import com.fine.hug.user.application.dto.UserLoginDto;
import com.fine.hug.user.domain.*;
import com.fine.hug.user.infra.UserBasicTranslate;
import com.fine.hug.user.infra.UserDoctorTranslate;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log
public class UserService {

    private final UserBasicRepository userBasicRepository;
//    private final UserDoctorRepository userDoctorRepository;
    private final UserRepository userRepository;

    public UserBasic createUserBasic(UserBasicCreateDto dto) {

        if (userRepository.findByUserId(dto.getUserId()).isPresent()) {
            throw new AlreadyIdException("이미 존재하는 아이디입니다.");
        }

        UserBasic userBasic = UserBasicTranslate.translate(dto);
        return userRepository.save(userBasic);
    }

    public UserDoctor createUserDoctor(UserDoctorCreateDto dto) {

        if (userRepository.findByUserId(dto.getUserId()).isPresent()) {
            throw new AlreadyIdException("이미 존재하는 아이디입니다.");
        }

        UserDoctor userDoctor = UserDoctorTranslate.translate(dto);
        return userRepository.save(userDoctor);
    }

    public User loginUser(UserLoginDto dto) {
        User user = userRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 존재하지 않는 아이디입니다. ", dto.getUserId())));

        if (user.getPassword().equals(dto.getPassword())) {
            return user;
        }

        throw new BusinessException(ErrorCode.HANDLE_ACCESS_DENIED, "비밀번호가 일치하지 않습니다.");
    }

}
