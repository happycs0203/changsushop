package com.changsu.project.changsushop.controller.dto;

import com.changsu.project.changsushop.domain.DeliveryStatus;
import lombok.Data;

/**
 * @desc 배송 수정 입력 값
 * @author ChangSu, Ham
 * @version 1.0
 */
@Data
public class DeliveryModifyCondition {

    private Long orderId;
    private Long deliveryId;
    private DeliveryStatus deliveryStatus;
}
