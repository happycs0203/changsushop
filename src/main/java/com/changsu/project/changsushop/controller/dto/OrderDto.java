package com.changsu.project.changsushop.controller.dto;

import com.changsu.project.changsushop.domain.DeliveryStatus;
import com.changsu.project.changsushop.domain.OrderStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {

    private Long orderId;

    private LocalDateTime orderDate;

    private String deliveryAddress;

    private Long memberId;

    private String memberName;

    private OrderStatus orderStatus;

    private Long deliveryId;

    private DeliveryStatus deliveryStatus;

    @QueryProjection
    public OrderDto(Long orderId, LocalDateTime orderDate, String deliveryAddress, Long memberId, String memberName, OrderStatus orderStatus, Long deliveryId, DeliveryStatus deliveryStatus) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
        this.memberId = memberId;
        this.memberName = memberName;
        this.orderStatus = orderStatus;
        this.deliveryId = deliveryId;
        this.deliveryStatus = deliveryStatus;
    }
}
