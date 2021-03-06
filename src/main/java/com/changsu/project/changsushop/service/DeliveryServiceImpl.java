package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.dto.DeliveryModifyCondition;
import com.changsu.project.changsushop.domain.Delivery;
import com.changsu.project.changsushop.domain.DeliveryStatus;
import com.changsu.project.changsushop.domain.Order;
import com.changsu.project.changsushop.domain.OrderStatus;
import com.changsu.project.changsushop.repository.delivery.DeliveryRepository;
import com.changsu.project.changsushop.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @desc 배송 서비스 구현
 * @author ChangSu, Ham
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryServiceImpl implements DeliveryService{

    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;

    /**
     * @desc 배송 상태 변경
     * @param deliveries
     * @return
     */
    @Override
    public String changeStatus(List<Long> deliveries){

        String result = "success";

        try {
            for (Long deliveryId : deliveries) {
                Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(() -> new IllegalArgumentException("해당 배송이 없습니다. id : " + deliveryId));
                delivery.changeDeliveryStatus(DeliveryStatus.COMP);
            }
        }catch (Exception e){
            result = "fail";
        }
        return result;
    }

    /**
     * @desc 배송 조건에 맞는 배송건 배송상테 변경
     * @param condition
     * @return
     */
    @Override
    public String changeStatusOne(DeliveryModifyCondition condition) {
        String result = "success";
        Long orderId = condition.getOrderId();
        Long deliveryId = condition.getDeliveryId();
        DeliveryStatus deliveryStatus = condition.getDeliveryStatus();
        try {

            Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다. orderId : " + orderId));

            if(order.getStatus().equals(OrderStatus.ORDER)){
                Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(() -> new IllegalArgumentException("해당 배송정보가 없습니다. id : " + deliveryId));
                delivery.changeDeliveryStatus(deliveryStatus);
            }else {
                result = "cancel";
            }

        } catch (Exception e) {
            result = "fail";
        }

        return result;
    }

}
