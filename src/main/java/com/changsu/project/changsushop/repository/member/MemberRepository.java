package com.changsu.project.changsushop.repository.member;

import com.changsu.project.changsushop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{

    public List<Member> findByName(String name);

    public Optional<Member> findByEmail(String email);

}
