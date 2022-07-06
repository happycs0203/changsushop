package com.changsu.project.changsushop;

import com.changsu.project.changsushop.auth.interceptor.LogInterceptor;
import com.changsu.project.changsushop.auth.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @desc 인터셉터 커스텀
 * @author ChangSu, Ham
 * @version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * @desc 로그 인터셉터와 로그인 인터셉터 추가
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1) //우선순위 1
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**","/js/**","/.ico", "/error");

        registry.addInterceptor(new LoginInterceptor())
                .order(2) //우선순위 2
                .addPathPatterns("/**") //모든곳에서 로그인 체크
                .excludePathPatterns("/", "/members/new", "/changeLocale" ,"/login", "/logout", "/css/**", "/js/**", "/error");
                //이 경로에서는 로그인체크 하지 않는다.

    }

    /**
     * @desc 언어 기본 값 설정
     * @return
     */
    @Bean
    public SessionLocaleResolver localeResolver(){
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(new Locale("ko"));
        return sessionLocaleResolver;
    }


}
