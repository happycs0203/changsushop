package com.changsu.project.changsushop.repository.order;

import com.changsu.project.changsushop.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
