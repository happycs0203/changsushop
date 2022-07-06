package com.changsu.project.changsushop.repository.delivery;


import com.changsu.project.changsushop.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @desc 배송 레포지토리 SpringDataJPA 를 사용하기 위한 인터페이스
 * @author ChangSu, Ham
 * @version 1.0
 */
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
