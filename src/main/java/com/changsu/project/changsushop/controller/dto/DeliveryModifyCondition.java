package com.changsu.project.changsushop.controller.dto;

import com.changsu.project.changsushop.domain.DeliveryStatus;
import lombok.Data;

@Data
public class DeliveryModifyCondition {

    private Long orderId;
    private Long deliveryId;
    private DeliveryStatus deliveryStatus;
}
