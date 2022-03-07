package com.changsu.project.changsushop.repository.order;

import com.changsu.project.changsushop.controller.dto.OrderDto;
import com.changsu.project.changsushop.controller.dto.OrderSearchCondition;
import com.changsu.project.changsushop.controller.dto.QOrderDto;
import com.changsu.project.changsushop.domain.*;
import com.changsu.project.changsushop.domain.item.QItem;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.changsu.project.changsushop.domain.QDelivery.*;
import static com.changsu.project.changsushop.domain.QMember.*;
import static com.changsu.project.changsushop.domain.QOrder.*;
import static com.changsu.project.changsushop.domain.QOrderItem.*;
import static com.changsu.project.changsushop.domain.item.QItem.*;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public OrderDto orderById(Long orderId) {
        OrderDto orderDto = queryFactory.select(new QOrderDto(
                        order.id,
                        order.createdDate,
                        delivery.address.address.concat(" ").concat(delivery.address.addressDetail),
                        member.id,
                        member.name,
                        order.status,
                        delivery.id,
                        delivery.status
                ))
                .from(order)
                .join(order.delivery, delivery)
                .join(order.member, member)
                .where(order.id.eq(orderId))
                .fetchOne();

        return orderDto;
    }


    public List<Order> orderList(OrderSearchCondition condition){
        List<Order> orders = queryFactory.select(order)
                .from(order)
                .join(order.member, member).fetchJoin()
                .join(order.delivery, delivery).fetchJoin()
                .where(
                        memberNameEq(condition.getMemberName()),
                        orderStatusEq(condition.getOrderStatus())
                )
                .fetch();

        return orders;
    }

    public Page<Order> orderPageList(OrderSearchCondition condition, Pageable pageable) {
        List<Order> orders = queryFactory.select(order)
                .from(order)
                .join(order.member, member)
                .join(order.delivery, delivery)
                .where(
                        memberNameEq(condition.getMemberName()),
                        orderStatusEq(condition.getOrderStatus())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(order.count())
                .from(order)
                .join(order.member, member)
                .join(order.delivery, delivery)
                .where(
                        memberNameEq(condition.getMemberName()),
                        orderStatusEq(condition.getOrderStatus())
                );

        return PageableExecutionUtils.getPage(orders, pageable, countQuery::fetchOne);
    }

    private BooleanExpression orderStatusEq(OrderStatus orderStatus) {
        if (orderStatus != null) {
            return order.status.eq(orderStatus);
        }
        return null;
    }

    private BooleanExpression memberNameEq(String memberName) {
        if (StringUtils.hasText(memberName)) {
            return member.name.eq(memberName);
        }
        return null;
    }

    public List<Order> findOrderItemsById(Long orderId) {
        List<Order> orderItems = queryFactory.select(order)
                .from(order)
                .join(order.orderItems, orderItem)
                .join(orderItem.item, item)
                .where(order.id.eq(orderId))
                .fetch();

        return orderItems;
    }

}
