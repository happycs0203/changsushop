package com.changsu.project.changsushop.controller.dto;

import com.changsu.project.changsushop.domain.DeliveryStatus;
import lombok.Data;

@Data
public class DeliverySearchCondition {

    private DeliveryStatus deliveryStatus;
    private String searchType;
    private String searchText;

}
