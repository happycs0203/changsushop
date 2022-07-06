package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.dto.OrderItemDto;
import com.changsu.project.changsushop.controller.dto.OrderSearchCondition;
import com.changsu.project.changsushop.controller.form.OrderSaveForm;
import com.changsu.project.changsushop.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @desc 주문 서비스 인터페이스
 * @author ChangSu, Ham
 * @version 1.0
 */
public interface OrderService {

    public Long save(OrderSaveForm form);

    public List<Order> orderList(OrderSearchCondition condition);

    public String cancel(Long orderId);

    public Order findById(Long orderId);

    public Page<Order> orderPageList(OrderSearchCondition condition, Pageable pageable);

    List<OrderItemDto> findOrderItemsById(Long orderId);
}
