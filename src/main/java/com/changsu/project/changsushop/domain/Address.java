package com.changsu.project.changsushop.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String address;
    private String addressDetail;
    private String zipcode;

    @Builder
    public Address(String address, String addressDetail, String zipcode) {
        this.address = address;
        this.addressDetail = addressDetail;
        this.zipcode = zipcode;
    }
}
