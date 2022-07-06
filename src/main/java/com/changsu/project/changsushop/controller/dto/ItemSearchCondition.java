package com.changsu.project.changsushop.controller.dto;

import lombok.Data;

/**
 * @desc 상품 조회 정보 DTO
 * @author ChangSu, Ham
 * @version 1.0
 */
@Data
public class ItemSearchCondition {
    Long categoryItemId;
    String dtype;
    String searchType;
    String searchText;
}
