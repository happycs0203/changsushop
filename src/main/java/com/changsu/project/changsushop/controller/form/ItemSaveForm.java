package com.changsu.project.changsushop.controller.form;

import lombok.Data;

/**
 * @desc 상품 정보 저장 폼
 * @author ChangSu, Ham
 * @version 1.0
 */
@Data
public class ItemSaveForm {

    private Long itemId;
    private int count;
    private int orderPrice;
}
