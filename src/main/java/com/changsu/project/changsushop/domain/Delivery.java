package com.changsu.project.changsushop.domain;

import com.changsu.project.changsushop.domain.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMP (ORDINAL일경우 1,2로 매핑이된다.) 꼭 STRING으로 사용한다.

    public Delivery(Address address, DeliveryStatus status) {
        this.address = address;
        this.status = status;
    }

    public void changeDeliveryStatus(DeliveryStatus deliveryStatus){
        this.setStatus(deliveryStatus);
    }
}
