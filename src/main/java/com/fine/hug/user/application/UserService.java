package com.fine.hug.user.application;

import com.fine.hug.exception.AlreadyIdException;
import com.fine.hug.user.application.dto.UserBasicCreateDto;
import com.fine.hug.user.application.dto.UserDoctorCreateDto;
import com.fine.hug.user.domain.UserBasic;
import com.fine.hug.user.domain.UserBasicRepository;
import com.fine.hug.user.domain.UserDoctor;
import com.fine.hug.user.domain.UserDoctorRepository;
import com.fine.hug.user.infra.UserBasicTranslate;
import com.fine.hug.user.infra.UserDoctorTranslate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserBasicRepository userBasicRepository;
    private final UserDoctorRepository userDoctorRepository;

    public UserBasic createUserBasic(UserBasicCreateDto dto) {
        if (userBasicRepository.findById(dto.getUserId()).isPresent()) {
            throw new AlreadyIdException("이미 존재하는 아이디입니다.");
        }

        UserBasic userBasic = UserBasicTranslate.translate(dto);
        return userBasicRepository.save(userBasic);
    }

    public UserDoctor createUserDoctor(UserDoctorCreateDto dto) {
        if (userDoctorRepository.findById(dto.getUserId()).isPresent()) {
            throw new AlreadyIdException("이미 존재하는 아이디입니다.");
        }

        UserDoctor userDoctor = UserDoctorTranslate.translate(dto);
        return userDoctorRepository.save(userDoctor);
    }


}
