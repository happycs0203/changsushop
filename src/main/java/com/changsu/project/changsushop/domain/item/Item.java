package com.changsu.project.changsushop.domain.item;

import com.changsu.project.changsushop.domain.Category;
import com.changsu.project.changsushop.domain.CategoryItem;
import com.changsu.project.changsushop.domain.base.BaseEntity;
import com.changsu.project.changsushop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //JOIN은 정규화된 상태, TABLE_PER_CLASS 테이블마다 분배, SINGLE_TABLE 한테이블에 다
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @Column(insertable = false, updatable = false)
    private String dtype;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems;

    //==비지니스 로직==//

    /**
     * stock 증가
     * @param quantity
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     * @param quantity
     */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
    //stockQuantity를 바꾸는 일이 생기면 setter로 바꾸는 것이 아닌 비지니스 로직을 통해서 한다.

}
