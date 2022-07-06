package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.domain.item.Item;

import java.util.List;

/**
 * @desc 상품 서비스 인터페이스
 * @author ChangSu, Ham
 * @version 1.0
 */
public interface ItemService {

    public List<Item> findAll();
}
