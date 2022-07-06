package com.changsu.project.changsushop.repository.member;

import com.changsu.project.changsushop.controller.dto.ItemSearchCondition;
import com.changsu.project.changsushop.controller.dto.MemberSearchCondition;
import com.changsu.project.changsushop.controller.form.MemberForm;
import com.changsu.project.changsushop.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @desc 멤퍼 레포지토리 커스텀 인터페이스 ( 여러개의 인터페이스를 사용하기 위함 )
 * @author ChangSu, Ham
 * @version 1.0
 */
public interface MemberRepositoryCustom {

    List<Member> search();

    List<Member> searchByName(String name);

    List<Member> searchByCondition(MemberSearchCondition condition);

    Page<MemberForm> searchPageByCondition(MemberSearchCondition condition, Pageable pageable);
}
