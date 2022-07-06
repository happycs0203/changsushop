package com.changsu.project.changsushop.repository.member;

import com.changsu.project.changsushop.controller.dto.MemberSearchCondition;
import com.changsu.project.changsushop.controller.form.MemberForm;
import com.changsu.project.changsushop.controller.form.QMemberForm;
import com.changsu.project.changsushop.domain.Member;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.changsu.project.changsushop.domain.QMember.*;

/**
 * @desc 멤퍼 레포지토리 커스텀 구현 QueryDSL을 사용
 * @author ChangSu, Ham
 * @version 1.0
 */
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * @desc 멤버 조회
     * @return
     */
    @Override
    public List<Member> search() {
        return queryFactory
                .selectFrom(member)
                .fetch();
    }

    /**
     * @desc 이름으로 멤버 정보 조회
     * @param name
     * @return
     */
    @Override
    public List<Member> searchByName(String name) {
        return queryFactory
                .selectFrom(member)
                .where(memberNameContains(name))
                .fetch();
    }


    /**
     * @desc 멤버 조회 객체에 해당하는 멤버 조회
     * @param condition
     * @return
     */
    @Override
    public List<Member> searchByCondition(MemberSearchCondition condition) {
        return queryFactory
                .selectFrom(member)
                .where(
                        memberConditionEq(condition)
                )
                .fetch();
    }

    /**
     * @desc 멤버 조회 객체에 해당하는 멤버 조회 및 페이징 정보
     * @param condition
     * @param pageable 페이징
     * @return
     */
    @Override
    public Page<MemberForm> searchPageByCondition(MemberSearchCondition condition, Pageable pageable) {
        List<MemberForm> members = queryFactory
                .select(new QMemberForm(
                        member.id,
                        member.name,
                        member.email,
                        member.address.address,
                        member.address.addressDetail,
                        member.address.zipcode
                ))
                .from(member)
                .where(memberConditionEq(condition))
                .orderBy(member.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(member.count())
                .from(member)
                .where(memberConditionEq(condition));


        return PageableExecutionUtils.getPage(members,pageable, () -> countQuery.fetchOne());
    }

    /**
     * @desc 동적 쿼리를 만듬
     * @param condition
     * @return
     */
    private BooleanExpression memberConditionEq(MemberSearchCondition condition) {
        if (StringUtils.hasText(condition.getSearchType())) {
            if(condition.getSearchType().equals("name")){
                return memberNameContains(condition.getSearchText());
            }else if(condition.getSearchType().equals("email")){
                return memberEmailStartsWith(condition.getSearchText());
            }
        }

        return null;
    }

    private BooleanExpression memberNameContains(String name) {
        if(StringUtils.hasText(name)){
            return member.name.contains(name);
        }
        return null;
    }

    private BooleanExpression memberEmailStartsWith(String email) {
        if (StringUtils.hasText(email)) {
            return member.email.startsWith(email);
        }
        return null;
    }


}
