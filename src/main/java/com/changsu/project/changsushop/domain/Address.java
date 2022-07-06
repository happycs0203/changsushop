package com.changsu.project.changsushop.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * @desc 주소 정보 엔티티 - 회원, 배송 에서 같이 사용됨
 * @author ChangSu, Ham
 * @version 1.0
 */
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String address; //주소
    private String addressDetail; //상세주소
    private String zipcode; //우편번호

    @Builder
    public Address(String address, String addressDetail, String zipcode) {
        this.address = address;
        this.addressDetail = addressDetail;
        this.zipcode = zipcode;
    }
}
