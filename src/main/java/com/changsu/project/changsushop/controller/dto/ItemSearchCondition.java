package com.changsu.project.changsushop.controller.dto;

import lombok.Data;

@Data
public class ItemSearchCondition {
    Long categoryItemId;
    String dtype;
    String searchType;
    String searchText;
}
