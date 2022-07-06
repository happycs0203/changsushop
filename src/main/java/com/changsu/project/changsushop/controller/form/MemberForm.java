package com.changsu.project.changsushop.controller.form;

import com.changsu.project.changsushop.domain.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @desc 멤버 조회 폼
 * @author ChangSu, Ham
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class MemberForm {

    private Long id;

    private String name;

    private String email;

    private String address;

    private String addressDetail;

    private String zipcode;

    @Builder
    @QueryProjection
    public MemberForm(Long id, String name, String email, String address, String addressDetail, String zipcode) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.addressDetail = addressDetail;
        this.zipcode = zipcode;
    }

    public MemberForm(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.address = member.getAddress().getAddress();
        this.addressDetail = member.getAddress().getAddressDetail();
        this.zipcode = member.getAddress().getZipcode();
    }

}
