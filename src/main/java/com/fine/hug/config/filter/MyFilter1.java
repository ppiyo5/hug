package com.fine.hug.config.filter;

import lombok.extern.java.Log;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log
public class MyFilter1 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //토큰: id, pw가 정상적으로 들어와서 로그인이 완료되었을 떄 토큰 생성 후 응답
        //요청할때마다 header에 Authorization에 value값으로 토큰 가지고 오면
        //그때 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지 검증 필요 (RSA, HS256)
        if(req.getMethod().equals("POST")) {
            String headerAuth = req.getHeader("Authorization");
            log.info("headerAuth: " +headerAuth);
            log.info("필터1");

            if(headerAuth.equals("cos")) {
                chain.doFilter(req, res);
            } else {
                PrintWriter out = res.getWriter();
                out.println("xxx");
            }
        }
    }
}
