package com.changsu.project.changsushop.repository.order;

import com.changsu.project.changsushop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
}
