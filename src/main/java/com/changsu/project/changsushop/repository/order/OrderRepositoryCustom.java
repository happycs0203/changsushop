package com.changsu.project.changsushop.repository.order;

import com.changsu.project.changsushop.controller.dto.OrderDto;
import com.changsu.project.changsushop.controller.dto.OrderSearchCondition;
import com.changsu.project.changsushop.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepositoryCustom {

    public OrderDto orderById(Long orderId);

    public List<Order> orderList(OrderSearchCondition condition);

    public Page<Order> orderPageList(OrderSearchCondition condition, Pageable pageable);
}
