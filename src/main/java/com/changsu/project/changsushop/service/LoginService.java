package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.domain.Member;

/**
 * @desc 로그인 서비스 인터페이스
 * @author ChangSu, Ham
 * @version 1.0
 */
public interface LoginService {

    Member login(String email, String password);
}
