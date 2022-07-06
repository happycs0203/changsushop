package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.dto.DeliveryModifyCondition;

import java.util.List;

/**
 * @desc 배송 서비스 인터페이스
 * @author ChangSu, Ham
 * @version 1.0
 */
public interface DeliveryService {

    String changeStatus(List<Long> deliveries);

    String changeStatusOne(DeliveryModifyCondition condition);

}
