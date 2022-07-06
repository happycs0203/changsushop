package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.domain.Member;
import com.changsu.project.changsushop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @desc 로그인 서비스 구현
 * @author ChangSu, Ham
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final MemberRepository memberRepository;

    /**
     * @desc 로그인 구현
     * @param email
     * @param password
     * @return
     */
    @Override
    public Member login(String email, String password) {

        Optional<Member> member = memberRepository.findByEmail(email);
        return member.filter(m -> m.checkPassword(password))
                .orElse(null);
    }
}
