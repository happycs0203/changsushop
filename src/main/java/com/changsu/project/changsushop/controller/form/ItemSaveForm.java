package com.changsu.project.changsushop.controller.form;

import lombok.Data;

@Data
public class ItemSaveForm {

    private Long itemId;
    private int count;
    private int orderPrice;
}
