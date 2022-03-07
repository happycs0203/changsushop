package com.changsu.project.changsushop.controller.form;

import com.changsu.project.changsushop.domain.Address;
import com.changsu.project.changsushop.domain.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class MemberSaveForm {

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "정확한 이메일을 입력해주세요.")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @NotBlank(message = "주소를 입력해주세요.")
    private String address;

    @NotBlank(message = "상세 주소를 입력해주세요.")
    private String addressDetail;

    @Size(min = 5, max = 5, message = "우편번호(5자)를 입력해주세요.")
    private String zipcode;

    private Role role = Role.USER;

    @Builder
    public MemberSaveForm(String email, String password, String name, String address, String addressDetail, String zipcode) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.addressDetail = addressDetail;
        this.zipcode = zipcode;
    }
}
