package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.dto.DeliveryModifyCondition;

import java.util.List;

public interface DeliveryService {

    public String changeStatus(List<Long> deliveries);

    public String changeStatusOne(DeliveryModifyCondition condition);

}
