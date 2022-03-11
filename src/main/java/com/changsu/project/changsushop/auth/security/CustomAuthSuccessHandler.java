package com.changsu.project.changsushop.auth.security;

import com.changsu.project.changsushop.auth.dto.SessionMember;
import com.changsu.project.changsushop.auth.interceptor.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final String DEFAULT_LOGIN_SUCCESS_URL = "/";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String uri = DEFAULT_LOGIN_SUCCESS_URL;

        String redirectURL = request.getParameter("redirectURL");

        if(redirectURL != null){
            uri = redirectURL;
        }

        CustomDetails userDetails = (CustomDetails) authentication.getPrincipal();

        SessionMember sessionMember = new SessionMember(userDetails.getMember());
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, sessionMember);

//        clearAuthenticationAttributes(request);
        response.sendRedirect(uri);

    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(SessionConst.LOGIN_MEMBER);
        }
    }

}
