package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.domain.Member;

public interface LoginService {

    Member login(String email, String password);
}
