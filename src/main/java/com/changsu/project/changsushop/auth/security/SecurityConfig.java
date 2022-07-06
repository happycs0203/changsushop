package com.changsu.project.changsushop.auth.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @desc Spring Security 설정들 활성화
 * @author ChangSu, Ham
 * @version 1.0
 */
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomDetailsService customDetailsService;
    private final CustomOAuth2UserService customOAuth2UserService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                    .anyRequest().permitAll()
                .and()
                    .formLogin() //로그인설정
                    .loginPage("/login")//로그인 페이지 url
                    .loginProcessingUrl("/login") //로그인 실행 url
                    .usernameParameter("email") //유저명 파라미터
                    .passwordParameter("password") //비밀번호 파라미터
                    .defaultSuccessUrl( "/") //성공시 기본 redirect url
                    .failureHandler(failureHandler()) //로그인 실패시 로직 사용자화
                    .successHandler(successHandler()) //로그인 성공시 로직 사용자화
                .and()
                    .logout()
                    .logoutUrl("/logout") //로그아웃 url
                    .logoutSuccessUrl("/home")
                    .deleteCookies("JSESSIONID","remember-me") //쿠키등록
                    .logoutSuccessHandler(logoutSuccessHandler()) //로그아웃 성공시 로직 사용자화
                .and()
                    .rememberMe() // 로그인 유지
                    .rememberMeParameter("remember-me")
                    .tokenValiditySeconds(3600)
                    .userDetailsService(customDetailsService) //remember-me 체크시 실행되는 로직 사용자화
                .and()
                    .oauth2Login() //OAuth2사용
                    .loginPage("/login")
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService); //OAuth2 사용 로직 사용자화
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomAuthSuccessHandler();
    }
    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new CustomAuthFailureHandler();
    }
    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

}
