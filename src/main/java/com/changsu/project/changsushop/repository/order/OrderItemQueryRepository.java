package com.changsu.project.changsushop.repository.order;

import com.changsu.project.changsushop.controller.dto.OrderItemDto;
import com.changsu.project.changsushop.controller.dto.QOrderItemDto;
import com.changsu.project.changsushop.domain.QOrderItem;
import com.changsu.project.changsushop.domain.item.QItem;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.changsu.project.changsushop.domain.QOrderItem.*;
import static com.changsu.project.changsushop.domain.item.QItem.*;

@Repository
public class OrderItemQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public OrderItemQueryRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<OrderItemDto> orderItemList(Long orderId) {
        List<OrderItemDto> orderItems = queryFactory.select(new QOrderItemDto(
                        orderItem.id,
                        orderItem.order.id,
                        item.id,
                        item.name,
                        orderItem.count
                ))
                .from(orderItem)
                .join(orderItem.item, item)
                .where(orderItem.order.id.eq(orderId))
                .fetch();

        return orderItems;

    }

    public Page<OrderItemDto> orderItemPageList(Long orderId, Pageable pageable) {
        List<OrderItemDto> orderItems = queryFactory.select(
                        new QOrderItemDto(
                                orderItem.id,
                                orderItem.order.id,
                                item.id,
                                item.name,
                                orderItem.count
                        ))
                .from(orderItem)
                .join(orderItem.item, item)
                .where(orderItem.order.id.eq(orderId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(orderItem.count())
                .from(orderItem)
                .where(orderItem.order.id.eq(orderId));

        return PageableExecutionUtils.getPage(orderItems, pageable, countQuery::fetchOne);
    }

}
