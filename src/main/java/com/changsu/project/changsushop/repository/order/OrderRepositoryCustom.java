package com.changsu.project.changsushop.repository.order;

import com.changsu.project.changsushop.controller.dto.OrderDto;
import com.changsu.project.changsushop.controller.dto.OrderSearchCondition;
import com.changsu.project.changsushop.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @desc 주문 커스텀 레포지토리 인터페이스
 * @author ChangSu, Ham
 * @version 1.0
 */
public interface OrderRepositoryCustom {

    OrderDto orderById(Long orderId);

    List<Order> orderList(OrderSearchCondition condition);

    Page<Order> orderPageList(OrderSearchCondition condition, Pageable pageable);
}
