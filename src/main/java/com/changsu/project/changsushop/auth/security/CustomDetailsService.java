package com.changsu.project.changsushop.auth.security;

import com.changsu.project.changsushop.domain.Member;
import com.changsu.project.changsushop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @desc 인증용 객체 UserDetailsService를 커스텀 구현 Spring Security
 * @author ChangSu, Ham
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class CustomDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * @desc 이메일을 받아서 DB에서 맴버를 조회합니다.
     * @param email
     * @return 조회한 멤버를 인증용 객체에 매핑합니다.
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member memberEntity = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. email : " + email));
        if(memberEntity!=null){
            return new CustomDetails(memberEntity);
        }
        return null;
    }
}
