package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.form.MemberSaveForm;
import com.changsu.project.changsushop.controller.form.MemberUpdateForm;
import com.changsu.project.changsushop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;


    @Test
    public void memberSaveTest() throws Exception{
        //given
        MemberSaveForm memberSaveForm = MemberSaveForm.builder()
                .name("함창수")
                .password("Hamchangsu020#")
                .email("happycs0203@naver.com")
                .address("서울시")
                .addressDetail("목동서로 130")
                .buildingName("목동4단지아파트")
                .zipcode("12345")
                .build();

        //when
        Long saveId = memberService.save(memberSaveForm);
        Member findMember = memberService.findById(saveId);

        Thread.sleep(10000);

        MemberUpdateForm memberUpdateForm = MemberUpdateForm.builder()
                .id(1L)
                .name("함창수1")
                .password("Hamchangsu020#")
                .passwordNew("Hamchangsu020#")
                .passwordNewConf("Hamchangsu020#")
                .address("서울시 양천구")
                .addressDetail("목동서로 130길")
                .buildingName("목동4 단지아파트")
                .zipcode("12355")
                .build();

        memberService.update(memberUpdateForm);

        Member updateMember = memberService.findById(1L);

        //then
        assertThat(saveId).isEqualTo(1L);
        assertThat(findMember.getName()).isEqualTo("함창수");
        assertThat(updateMember.getName()).isEqualTo("함창수1");

    }


}