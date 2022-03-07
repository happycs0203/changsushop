package com.changsu.project.changsushop.controller.form;

import com.changsu.project.changsushop.domain.Address;
import com.changsu.project.changsushop.domain.Member;
import com.changsu.project.changsushop.domain.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class MemberUpdateForm {

    @NotNull(message = "회원번호가 없습니다.")
    private Long id;

    private String email;

    private String password;

    private String passwordNew;

    private String passwordNewConf;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "주소를 입력해주세요.")
    private String address;

    @NotBlank(message = "상세 주소를 입력해주세요.")
    private String addressDetail;

    @Size(min = 5, max = 5, message = "우편번호(5자)를 입력해주세요.")
    private String zipcode;

    private Role role = Role.USER;

    @Builder
    public MemberUpdateForm(Long id, String email, String password,String passwordNew, String passwordNewConf, String name, String address, String addressDetail, String zipcode) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.passwordNew = passwordNew;
        this.passwordNewConf = passwordNewConf;
        this.name = name;
        this.address = address;
        this.addressDetail = addressDetail;
        this.zipcode = zipcode;
    }

    @Builder
    public MemberUpdateForm(Long id, String password, String name, String address, String addressDetail, String zipcode) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.address = address;
        this.addressDetail = addressDetail;
        this.zipcode = zipcode;
    }

    public MemberUpdateForm(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.address = member.getAddress().getAddress();
        this.addressDetail = member.getAddress().getAddressDetail();
        this.zipcode = member.getAddress().getZipcode();
    }

    public MemberUpdateForm memberToMemberUpdateForm(Member member) {
        return MemberUpdateForm.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .address(member.getAddress().getAddress())
                .addressDetail(member.getAddress().getAddressDetail())
                .zipcode(member.getAddress().getZipcode())
                .build();
    }

    public boolean passwordCheck(){
        return passwordNew.equals(passwordNewConf) == true ? true : false;
    }
}
