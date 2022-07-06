package com.changsu.project.changsushop.repository.delivery;

import com.changsu.project.changsushop.controller.dto.DeliverySearchCondition;
import com.changsu.project.changsushop.domain.*;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

import java.util.List;

import static com.changsu.project.changsushop.domain.QDelivery.*;
import static com.changsu.project.changsushop.domain.QMember.*;
import static com.changsu.project.changsushop.domain.QOrder.*;

/**
 * @desc 배송 레포지토리 QueryDSL 구현
 * @author ChangSu, Ham
 * @version 1.0
 */
@Repository
public class DeliveryQueryRepository {

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    public DeliveryQueryRepository(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    public Page<Delivery> searchDeliveryList(DeliverySearchCondition condition, Pageable pageable) {

        List<Delivery> deliveries = queryFactory.select(delivery)
                .from(delivery)
                .join(delivery.order, order)
                .join(order.member, member)
                .where(
                        deliveryStatusEq(condition.getDeliveryStatus()),
                        searchTypeEq(condition.getSearchType(), condition.getSearchText())
                )
                .where(order.status.eq(OrderStatus.ORDER))
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(delivery.count())
                .from(delivery)
                .join(delivery.order, order)
                .where(order.status.eq(OrderStatus.ORDER));


        return PageableExecutionUtils.getPage(deliveries, pageable, countQuery::fetchOne);
    }

    private BooleanExpression deliveryStatusEq(DeliveryStatus deliveryStatus) {
        return deliveryStatus == null ? null : delivery.status.eq(deliveryStatus);
    }

    private BooleanExpression searchTypeEq(String searchType, String searchText) {
        if(StringUtils.hasText(searchText)){
            if(searchType.equals("name")){
                return member.name.eq(searchText);
            }else if(searchType.equals("orderId")){
                return order.id.eq(Long.parseLong(searchText));
            }
        }
        return null;
    }

}
