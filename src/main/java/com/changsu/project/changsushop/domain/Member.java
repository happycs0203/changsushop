package com.changsu.project.changsushop.domain;

import com.changsu.project.changsushop.controller.form.MemberSaveForm;
import com.changsu.project.changsushop.controller.form.MemberUpdateForm;
import com.changsu.project.changsushop.domain.base.BaseTimeEntity;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @desc 회원 정보 엔티티
 * @author ChangSu, Ham
 * @version 1.0
 */
@Entity
@Getter @Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email; //이메일

    private String password; //패스워드

    private String name; //이름

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; //롤

    @Embedded
    private Address address; //주소

    @OneToMany(mappedBy = "member") //매핑된 거울일뿐이야.
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(String email, String password, String name){
        this(email, password, name , new Address("", "",""));
    }

    @Builder
    public Member(String email, String password, String name, Address address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.role = Role.USER;
    }

    public Member(String email, String name, Role role) {
        this.email = email;
        encryptPassword("");
        this.name = name;
        this.role = role;
        this.address = new Address("", "", "");
    }

    public Member(MemberSaveForm form) {
        this.email = form.getEmail();
        encryptPassword(form.getPassword());
        this.name = form.getName();
        this.address = new Address(form.getAddress(), form.getAddressDetail(), form.getZipcode());
        this.role = form.getRole();
    }

    public void updateMemberPassword(MemberUpdateForm member) {
        this.email = member.getEmail();
        this.name = member.getName();
        encryptPassword(member.getPasswordNew());
        this.address = new Address(member.getAddress(), member.getAddressDetail(), member.getZipcode());
    }

    public void updateMember(MemberUpdateForm member) {
        this.email = member.getEmail();
        this.name = member.getName();
        this.address = new Address(member.getAddress(), member.getAddressDetail(), member.getZipcode());
        this.role = member.getRole();
    }

    public boolean checkNewPassword(String newPassword){
        return this.password.equals(newPassword) == true ? true : false;
    }

    public Member updateName(String name) {
        this.name = name;
        return this;
    }

    public boolean checkPassword(String password){
        return BCrypt.checkpw(password, this.password);
    }

    public void encryptPassword(String password){
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
