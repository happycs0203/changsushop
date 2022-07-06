package com.changsu.project.changsushop.repository.member;

import com.changsu.project.changsushop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @desc 멤버 레포지토리 인터페이스 - Spring Data JPA, 멤버 레포지토리 커스텀 상속
 * @author ChangSu, Ham
 * @version 1.0
 */
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{

    public List<Member> findByName(String name);

    public Optional<Member> findByEmail(String email);

    @Query("select m from Member m where m.name in :names")
    List<Member> findByNames(@Param("names") List<String> names);

}
