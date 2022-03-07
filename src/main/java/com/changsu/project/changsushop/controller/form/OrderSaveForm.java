package com.changsu.project.changsushop.controller.form;

import lombok.Data;

import java.util.List;

@Data
public class OrderSaveForm {

    private Long memberId;
    private List<ItemSaveForm> items;
}
