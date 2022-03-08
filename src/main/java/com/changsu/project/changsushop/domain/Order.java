package com.changsu.project.changsushop.domain;

import com.changsu.project.changsushop.domain.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter @Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    //member를 변경하고 싶으면 FK를 변경해야됨 객체는 orders와 member 2군데를 수정해야된다.

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    //persist(orderItemA), persist(orderItemB) , persist(orderItemC) -> persist(order)이것만 하면 한번에 다 된다.

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL) //기본적으로 저장하고 싶으면 각각 다해야된다.
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    //order_date
    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER, CANCEL]

    //==연관관계 메서드== 컨트롤 하는 쪽이 가지고 있으면 좋다.//
    public void changeMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드==//
    //오더만 생성하는게 아니라 Delivery, OrderItem 여러가지 연관관계가 들어가기때문에 별도의 메소드가 있으면 좋다.
    //생성시점에서 createOrder만 보면된다.
    public static Order createOrder(Member member, Delivery delivery, List<OrderItem> orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem: orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    /**
     * 비지니스 로직
     */
    public void cancel(){
        //배송이 됬으면 취소가 불가능해
        if(delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        //강조할때나 똑같은 값이 있을때 this를 써도 상관없다.
        this.setStatus(OrderStatus.CANCEL);
        System.out.println("getStatus() = " + getStatus());
        for (OrderItem orderItem: orderItems) {
            orderItem.cancel();
        }
    }

    //==조회 로직==//

    /**
     * 전체 주문 가격 조회
     * @return
     */
    public int getTotalPrice(){
        int totalPrice = 0;
        for (OrderItem orderItem: orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
