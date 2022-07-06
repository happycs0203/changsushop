package com.changsu.project.changsushop.repository.order;

import com.changsu.project.changsushop.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @desc 주문 상품 레포지토리 QueryDSL 사용
 * @author ChangSu, Ham
 * @version 1.0
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
