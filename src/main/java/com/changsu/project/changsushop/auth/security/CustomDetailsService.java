package com.changsu.project.changsushop.auth.security;

import com.changsu.project.changsushop.domain.Member;
import com.changsu.project.changsushop.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomDetailsService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member memberEntity = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. email : " + email));
        if(memberEntity!=null){
            return new CustomDetails(memberEntity);
        }
        return null;
    }
}
