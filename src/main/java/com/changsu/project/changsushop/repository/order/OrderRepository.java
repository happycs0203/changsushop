package com.changsu.project.changsushop.repository.order;

import com.changsu.project.changsushop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @desc 주문 레포지토리 인터페이스 - Spring Data JPA, 주문 레포지토리 커스텀 상속
 * @author ChangSu, Ham
 * @version 1.0
 */
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {
}
