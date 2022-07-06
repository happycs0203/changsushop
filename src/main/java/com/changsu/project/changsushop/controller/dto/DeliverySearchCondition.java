package com.changsu.project.changsushop.controller.dto;

import com.changsu.project.changsushop.domain.DeliveryStatus;
import lombok.Data;

/**
 * @desc 배송 조회 입력 값
 * @author ChangSu, Ham
 * @version 1.0
 */
@Data
public class DeliverySearchCondition {

    private DeliveryStatus deliveryStatus;
    private String searchType;
    private String searchText;

}
