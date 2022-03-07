package com.changsu.project.changsushop.domain;

import com.changsu.project.changsushop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    //셀프로 양방향으로 건것이다. 일반적인 상황과 똑같이 매핑하면된다.
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    //==연관관계 메서드==
    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }



}
