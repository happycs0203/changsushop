package com.changsu.project.changsushop.domain;

import com.changsu.project.changsushop.domain.base.BaseEntity;
import com.changsu.project.changsushop.domain.item.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

/**
 * @desc 주문 상품 매핑 엔티티
 * @author ChangSu, Ham
 * @version 1.0
 */
@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문가격

    private int count; //주문 수량

    //==생성메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice ,int count){
        //쿠폰 받거나 할인을 받을 수 있기 때문에 orderPrice는 Item의 getPrice를 하면 안된다.
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        //오더아이템을 생성하면서 재고까지 없앨 수 있다.
        item.removeStock(count);
        return orderItem;
    }

    //==비지니스 로직==//
    public void cancel() {
        getItem().addStock(count);
    }

    /**
     * 주문상태  전체 가격조회
     * @return
     */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }


}
