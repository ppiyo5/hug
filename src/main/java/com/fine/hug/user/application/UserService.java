package com.fine.hug.user.application;

import com.fine.hug.user.application.dto.UserCreateDto;
import com.fine.hug.user.domain.UserBasicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserBasicRepository userBasicRepository;

    @Transactional
    public Long save(UserCreateDto dto) {
        return userBasicRepository.save(dto.toEntity()).getId();
    }
}
