package com.changsu.project.changsushop.repository.item;

import com.changsu.project.changsushop.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
