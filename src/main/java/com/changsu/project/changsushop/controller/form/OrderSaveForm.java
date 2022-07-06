package com.changsu.project.changsushop.controller.form;

import lombok.Data;

import java.util.List;


/**
 * @desc 주문 정보 저장
 * @author ChangSu, Ham
 * @version 1.0
 */
@Data
public class OrderSaveForm {

    private Long memberId;
    private List<ItemSaveForm> items;
}
