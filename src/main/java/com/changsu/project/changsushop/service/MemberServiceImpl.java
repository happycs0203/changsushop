package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.form.MemberSaveForm;
import com.changsu.project.changsushop.controller.form.MemberUpdateForm;
import com.changsu.project.changsushop.domain.Address;
import com.changsu.project.changsushop.domain.Member;
import com.changsu.project.changsushop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @desc 멤버 서비스 구현
 * @author ChangSu, Ham
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    /**
     * @desc id에 맞는 멤버 조회
     * @param id
     * @return
     */
    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id :" + id));
    }

    /**
     * @desc id에 맞는 멤버 조회 MemberUpdateForm으로 리턴
     * @param id
     * @return
     */
    @Override
    public MemberUpdateForm findByIdMUF(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id :" + id));
        return new MemberUpdateForm(member);
    }

    /**
     * @desc 멤버 생성
     * @param memberSaveForm
     * @return
     */
    @Override
    @Transactional
    public Long save(MemberSaveForm memberSaveForm) {

        Member searchMember = memberRepository.findByEmail(memberSaveForm.getEmail()).orElse(null);

        if(searchMember == null){
            Member member = new Member(memberSaveForm);
            Member saveMember = memberRepository.save(member);
            return saveMember.getId();
        }
        return null;
    }

    /**
     * @desc memberUpdateForm에 들어온 값으로 수정
     * @param memberUpdateForm
     * @return
     */
    @Override
    @Transactional
    public Long update(MemberUpdateForm memberUpdateForm) {
        Member findMember = memberRepository.findById(memberUpdateForm.getId()).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id : " + memberUpdateForm.getId()));
//        findMember.updateMember(memberUpdateForm);
        String passwordNew = memberUpdateForm.getPasswordNew();
        String passwordNewConf = memberUpdateForm.getPasswordNewConf();
        //새 비밀번호가 있으면 비밀번호를 UPDATE
        if(StringUtils.hasText(passwordNew) && StringUtils.hasText(passwordNewConf)){
            findMember.updateMemberPassword(memberUpdateForm);
        }else{
            findMember.updateMember(memberUpdateForm);
        }
        return findMember.getId();
    }

    /**
     * @desc 멤버 비밀번호 확인
     * @param memberUpdateForm
     * @return
     */
    @Override
    public boolean checkPassword(MemberUpdateForm memberUpdateForm) {
        Member member = memberRepository.findById(memberUpdateForm.getId()).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id :" + memberUpdateForm.getId()));
        return member.checkPassword(memberUpdateForm.getPassword());
    }

    /**
     * @desc 회원 삭제
     * @param memberUpdateForm
     */
    @Override
    @Transactional
    public void delete(MemberUpdateForm memberUpdateForm) {
        Member findMember = memberRepository.findById(memberUpdateForm.getId()).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id : " + memberUpdateForm.getId()));
        memberRepository.delete(findMember);
    }

}
