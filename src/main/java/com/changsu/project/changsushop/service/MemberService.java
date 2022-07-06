package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.form.MemberSaveForm;
import com.changsu.project.changsushop.controller.form.MemberUpdateForm;
import com.changsu.project.changsushop.domain.Member;

/**
 * @desc 멤버 서비스 인터페이스
 * @author ChangSu, Ham
 * @version 1.0
 */
public interface MemberService {

    public Member findById(Long id);

    public MemberUpdateForm findByIdMUF(Long id);

    public Long save(MemberSaveForm member);

    public Long update(MemberUpdateForm member);

    public boolean checkPassword(MemberUpdateForm member);

    public void delete(MemberUpdateForm member);

}
