package com.changsu.project.changsushop.auth.dto;

import com.changsu.project.changsushop.domain.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionMember implements Serializable {

    private Long id;
    private String name;
    private String email;

    public SessionMember(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public SessionMember(String name) {
        this.name = name;
    }

    public SessionMember(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
