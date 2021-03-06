package com.changsu.project.changsushop.auth.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @desc 로그인 실패 핸들러 구현 Spring Security
 * @author ChangSu, Ham
 * @version 1.0
 */
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

    private final String DEFAULT_FAILURE_URL = "/login?error=true";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorMessage = null;

        String email = request.getParameter("email");
        String password = request.getParameter("password");


        if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException){
            errorMessage = "아이디나 비밀번호가 맞지 않습니다. 확인해주세요.";
        }else if(exception instanceof DisabledException){
            errorMessage = "계정이 비활성화 되었습니다.";
        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage = "비밀번호 유효기간이 만료 되었습니다.";
        } else {
            errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다.";
        }

        request.setAttribute("errorMessage", errorMessage);
        
        request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);

    }
}
