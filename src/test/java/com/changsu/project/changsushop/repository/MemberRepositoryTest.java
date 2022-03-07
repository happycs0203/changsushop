package com.changsu.project.changsushop.repository;

import com.changsu.project.changsushop.controller.dto.MemberSearchCondition;
import com.changsu.project.changsushop.controller.form.MemberForm;
import com.changsu.project.changsushop.domain.Member;
import com.changsu.project.changsushop.repository.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void memberTest() throws Exception{
        //given
        Member member1 = new Member("happycs023", "1234", "함창수1");
        Member member2 = new Member("happycs024", "1234", "함창수2");
        em.persist(member1);
        em.persist(member2);

        //when

        em.flush();
        em.clear();

        List<Member> results = em.createQuery("select m from Member m", Member.class).getResultList();

        //then
        assertThat(results).extracting("name").containsExactly("함창수1","함창수2");
    }

    @Test
    public void memberRepositoryTest() throws Exception{
        //given
        Member member1 = new Member("happycs023", "1234", "함창수1");
        Member member2 = new Member("happycs024", "1234", "함창수2");
        memberRepository.save(member1);
        memberRepository.save(member2);
        
        //when

        List<Member> results = memberRepository.findAll();
        List<Member> results_name = memberRepository.findByName("함창수1");

        //then

        assertThat(results).extracting("name").containsExactly("함창수1", "함창수2");
        assertThat(results.size()).isEqualTo(2);
        assertThat(results_name).extracting("name").containsExactly("함창수1");

    }
    
    @Test
    public void querydslTest() throws Exception{
        //given
        Member member1 = new Member("happycs023", "1234", "함창수1");
        Member member2 = new Member("happycs024", "1234", "함창수2");
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when

        List<Member> search = memberRepository.search();
        List<Member> search_name = memberRepository.searchByName("함창수1");

        //then

        assertThat(search).extracting("name").containsExactly("함창수1", "함창수2");
        assertThat(search.size()).isEqualTo(2);
        assertThat(search_name).extracting("name").containsExactly("함창수1");


    }

    @Test
    public void querydslPageTest() throws Exception{
        //given
        Member member1 = new Member("happycs023", "1234", "함창수1");
        Member member2 = new Member("happycs024", "1234", "함창수2");
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when

        MemberSearchCondition condition = new MemberSearchCondition();
        PageRequest pageRequest = PageRequest.of(1, 2);

        System.out.println("pageRequest.getPageSize() = " + pageRequest.getPageSize());
        System.out.println("pageRequest.getOffset() = " + pageRequest.getOffset());
        Page<MemberForm> search = memberRepository.searchPageByCondition(condition, pageRequest);

        //then
//        assertThat(search.getTotalElements()).isEqualTo(2);
//        assertThat(search.getTotalPages()).isEqualTo(2);
//        assertThat(search.getContent()).extracting("name").containsExactly("함창수1");

        assertThat(search.getTotalElements()).isEqualTo(2);
        assertThat(search.getTotalPages()).isEqualTo(1);
        assertThat(search.getContent()).extracting("name").containsExactly("함창수1","함창수2");


    }




}