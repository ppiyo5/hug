package com.fine.hug.user.application;

import com.fine.hug.config.jwt.JwtAuthenticationFilter;
import com.fine.hug.exception.AlreadyIdException;
import com.fine.hug.user.application.dto.UserBasicCreateDto;
import com.fine.hug.user.application.dto.UserDoctorCreateDto;
import com.fine.hug.user.domain.*;
import com.fine.hug.user.infra.UserBasicTranslate;
import com.fine.hug.user.infra.UserDoctorTranslate;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log
public class UserService {

    private final UserBasicRepository userBasicRepository;
    private final UserDoctorRepository userDoctorRepository;
    private final UserRepository userRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final PasswordEncoder passwordEncoder;

    public UserBasic createUserBasic(UserBasicCreateDto dto) {

        if (userRepository.findByUserId(dto.getUserId()).isPresent()) {
            throw new AlreadyIdException("이미 존재하는 아이디입니다.");
        }

        UserBasic userBasic = UserBasicTranslate.translate(dto);
        return userBasicRepository.save(userBasic);
    }

    public UserDoctor createUserDoctor(UserDoctorCreateDto dto) {

        if (userRepository.findByUserId(dto.getUserId()).isPresent()) {
            throw new AlreadyIdException("이미 존재하는 아이디입니다.");
        }

        UserDoctor userDoctor = UserDoctorTranslate.translate(dto);
        return userDoctorRepository.save(userDoctor);
    }

//    @Override
//    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//
//        com.fine.hug.user.domain.User user = userRepository.findByUserId(userId)
//                .orElseThrow(() -> new UsernameNotFoundException(userId));
//        log.info("userId: "+ userId);
//
//        return new User(user.getUserId(), user.getPassword(), authorities(user.getRole()));
//    }
//
//    private Collection<? extends GrantedAuthority> authorities(Set<Role> roles) {
//        return roles.stream()
//                .map(r -> new SimpleGrantedAuthority(r.name()))
//                .collect(Collectors.toSet());
//    }

}
