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

    @Override
    public List<Order> orderList(OrderSearchCondition condition) {
        return orderRepository.orderList(condition);
    }

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

    @Override
    public Order findById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다. id : " + orderId));

        return order;
    }

    @Override
    public Page<Order> orderPageList(OrderSearchCondition condition, Pageable pageable) {
        return orderRepository.orderPageList(condition, pageable);
    }

    @Override
    public List<OrderItemDto> findOrderItemsById(Long orderId) {
        return orderItemQueryRepository.orderItemList(orderId);
    }
}
