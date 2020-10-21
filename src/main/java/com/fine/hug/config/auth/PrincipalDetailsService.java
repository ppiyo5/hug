package com.fine.hug.config.auth;

import com.fine.hug.user.domain.User;
import com.fine.hug.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//http://localhost:8080/login
@Service
@RequiredArgsConstructor
@Log
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("PrincipalDetailsService의 loadUserByUsername()");
        User userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId));
        log.info("userEntity: " + userEntity.getRole());
        log.info("=============================================");
        return new PrincipalDetails(userEntity);
    }
}
