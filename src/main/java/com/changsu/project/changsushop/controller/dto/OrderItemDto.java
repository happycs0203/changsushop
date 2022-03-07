package com.changsu.project.changsushop.controller.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class OrderItemDto {

    private Long orderItemId;

    private Long orderId;

    private Long itemId;

    private String itemName;

    private int count;

    @QueryProjection
    public OrderItemDto(Long orderItemId, Long orderId, Long itemId, String itemName, int count) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.count = count;
    }
}
