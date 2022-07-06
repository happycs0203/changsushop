package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.domain.item.Item;
import com.changsu.project.changsushop.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @desc 상품 서비스 구현
 * @author ChangSu, Ham
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    /**
     * @desc 전체 상품 조회
     * @return
     */
    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}
