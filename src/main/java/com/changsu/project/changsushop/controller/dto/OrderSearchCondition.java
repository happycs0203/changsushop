package com.changsu.project.changsushop.controller.dto;

import com.changsu.project.changsushop.domain.OrderStatus;
import lombok.Data;

/**
 * @desc 주문 조회 입력 값
 * @author ChangSu, Ham
 * @version 1.0
 */
@Data
public class OrderSearchCondition {

    private String memberName; //회원 이름
    private OrderStatus orderStatus; //주문상태 [ORDER, CANCEL]

}
