package com.changsu.project.changsushop;

import com.changsu.project.changsushop.auth.interceptor.LogInterceptor;
import com.changsu.project.changsushop.auth.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**","/js/**","/.ico", "/error");

        registry.addInterceptor(new LoginInterceptor())
                .order(2)
                .addPathPatterns("/**") //모든곳에서 로그인 체크
                .excludePathPatterns("/", "/members/new", "/login", "/logout", "/css/**", "/js/**", "/error");
                //이 경로에서는 로그인체크 하지 않는다.
    }

}
