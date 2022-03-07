package com.changsu.project.changsushop.controller.dto;

import com.changsu.project.changsushop.domain.OrderStatus;
import lombok.Data;

@Data
public class OrderSearchCondition {

    private String memberName; //회원 이름
    private OrderStatus orderStatus; //주문상태 [ORDER, CANCEL]

}
