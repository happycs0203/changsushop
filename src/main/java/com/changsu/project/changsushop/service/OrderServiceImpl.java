package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.dto.OrderItemDto;
import com.changsu.project.changsushop.controller.dto.OrderSearchCondition;
import com.changsu.project.changsushop.controller.form.ItemSaveForm;
import com.changsu.project.changsushop.controller.form.OrderSaveForm;
import com.changsu.project.changsushop.domain.*;
import com.changsu.project.changsushop.domain.item.Item;
import com.changsu.project.changsushop.repository.item.ItemRepository;
import com.changsu.project.changsushop.repository.member.MemberRepository;
import com.changsu.project.changsushop.repository.delivery.DeliveryRepository;
import com.changsu.project.changsushop.repository.order.OrderItemQueryRepository;
import com.changsu.project.changsushop.repository.order.OrderItemRepository;
import com.changsu.project.changsushop.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc 주문 서비스 구현
 * @author ChangSu, Ham
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final DeliveryRepository deliveryRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;
    private final OrderItemQueryRepository orderItemQueryRepository;


    /**
     * @desc 주문 생성
     * @param form
     * @return
     */
    @Override
    @Transactional
    public Long save(OrderSaveForm form) {
        Long memberId = form.getMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id : " + memberId));

        Delivery delivery = new Delivery(member.getAddress(), DeliveryStatus.READY);

        List<ItemSaveForm> itemSaveForms = form.getItems();
        List<OrderItem> orderItems = new ArrayList<>();

        for (ItemSaveForm itemSaveForm : itemSaveForms) {
            Long itemId = itemSaveForm.getItemId();
            Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id : " + itemId));
            int price = item.getPrice();
            int count = itemSaveForm.getCount();
            OrderItem orderItem = OrderItem.createOrderItem(item, price, count);
            orderItems.add(orderItem);
        }

        Order order = Order.createOrder(member, delivery, orderItems);
        Order save = orderRepository.save(order);

        return order.getId();
    }

    /**
     * @desc 주문 리스트 조회
     * @param condition 주문 조회 조건
     * @return
     */
    @Override
    public List<Order> orderList(OrderSearchCondition condition) {
        return orderRepository.orderList(condition);
    }

    /**
     * @desc 주문 취소 상태 변경 (dirty checking 변겸감지)
     * @param orderId
     * @return
     */
    @Override
    @Transactional
    public String cancel(Long orderId) {
        String result = "success";
        try {
            Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다. id : " + orderId));
            order.cancel();
        } catch (Exception e) {
            log.error(e.getMessage());
            result = e.getMessage();
        }

        return result;
    }

    /**
     * @desc orderId에 맞는 주문 조회
     * @param orderId
     * @return
     */
    @Override
    public Order findById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다. id : " + orderId));

        return order;
    }

    /**
     * @desc 주문 리스트 페이징
     * @param condition
     * @param pageable
     * @return
     */
    @Override
    public Page<Order> orderPageList(OrderSearchCondition condition, Pageable pageable) {
        return orderRepository.orderPageList(condition, pageable);
    }

    /**
     * @desc 주문 상품 상세 정보 조회
     * @param orderId
     * @return
     */
    @Override
    public List<OrderItemDto> findOrderItemsById(Long orderId) {
        return orderItemQueryRepository.orderItemList(orderId);
    }
}
