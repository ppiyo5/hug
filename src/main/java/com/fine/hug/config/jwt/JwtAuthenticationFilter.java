package com.fine.hug.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fine.hug.config.auth.PrincipalDetails;
import com.fine.hug.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


// 스프링 시큐리티에서 기본 formLogin url인 /login으로 요청을 해서
// username, password를 전송하면 (post)
// UsernamePasswordAuthenticationFilter가 동작하며 로그인 실행
@RequiredArgsConstructor
@Log
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("JwtAuthenticationFilter: 로그인 시도중");

        // 1. username, password 받아서
        try {
//            BufferedReader br = request.getReader();
//            String input = null;
//            while ((input = br.readLine()) != null) {
//                System.out.println(input);
//            }

            ObjectMapper mapper = new ObjectMapper(); // json data parsing
            User user = mapper.readValue(request.getInputStream(), User.class);
            log.info("user: " + user);
            String jsonData = mapper.writeValueAsString(user);
            log.info("jsonData: " + jsonData);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword()); //토큰 만들기

            // PrincipalDetailsService의 loadUserByUsername() 함수가 실행된 후 정상이면 authentication이 리턴됨
            // DB에 있는 username과 password가 일치한다.
            Authentication authentication = authenticationManager.authenticate(token); //내가 로그인한 정보 담김

            // => 로그인이 되었다는 뜻 (임시)
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            log.info("user::: " + principalDetails.getUser());
            log.info("로그인 완료: principalDetails.getUser().getUserId(): " + principalDetails.getUser().getUserId()); // 로그인 정상적으로 되었다는 뜻
            log.info("로그인 완료: principalDetails.getUser().getPassword(): " + principalDetails.getUser().getPassword());

            // authentication 객체가 session 영역에 저장을principalDetails 해야하고 그 방법은 return을 해주는 것.
            //리턴의 이유는 권한 관리를 security가 대신 해주기 때문에 편하기 위해 하는 것
            // 굳이 JWT 토큰을 사용하면서 세션을 만들 이유가 없지만 권한 처리때문에 세션에 넣음.

            // JWT 토큰 만들기
            return authentication;

        } catch (IOException e) {
            e.printStackTrace();
        }
        // 2. 정상인지 로그인 시도 해보기. authenticationManager로 로그인 시도를 하면
        // PrincipalDetailsService가 호출 -> loadUserByUsername() 함수가 자동 실행됨
        // 3. PrincipalDetails를 세션에 담고 (권한 관리를 위해서)
        // 4. JWT 토큰을 만들어서 응답해주면 됨.
        return null;
    }

    // attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행됨
    // JWT 토큰을 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해주면 됨
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("successfulAuthentication 실행됨: 인증이 완료되었다는 뜻!");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        //토큰 만들기 Hash 암호방식
        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME)) //토큰 마료시간: 30분
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("username", principalDetails.getUser().getUserId())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET)); //시크릿값 (서버가 알고있어야함)

        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken); //사용자에게 보여질 응답값
    }
}
