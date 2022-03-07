package com.changsu.project.changsushop.auth.argumentresolver;

import com.changsu.project.changsushop.auth.dto.SessionMember;
import com.changsu.project.changsushop.domain.Member;
import com.changsu.project.changsushop.auth.interceptor.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("supportsParameter 실행");
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class); //Login어노테이션이 parameter에 있는가?
        boolean hasMemberType = SessionMember.class.isAssignableFrom(parameter.getParameterType()); //@Login Member class가 들어온다. MemberType이냐?

        return hasLoginAnnotation && hasMemberType; //true 면 resolveArgument 가 실행된다.
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        
        log.info("resolveArgument 실행");

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);

        if (session == null) {
            return null;
        }

        return session.getAttribute(SessionConst.LOGIN_MEMBER);

    }
}
