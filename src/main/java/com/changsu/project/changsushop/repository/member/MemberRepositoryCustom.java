package com.changsu.project.changsushop.repository.member;

import com.changsu.project.changsushop.controller.dto.ItemSearchCondition;
import com.changsu.project.changsushop.controller.dto.MemberSearchCondition;
import com.changsu.project.changsushop.controller.form.MemberForm;
import com.changsu.project.changsushop.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {

    public List<Member> search();

    public List<Member> searchByName(String name);

    public List<Member> searchByCondition(MemberSearchCondition condition);

    public Page<MemberForm> searchPageByCondition(MemberSearchCondition condition, Pageable pageable);
}
