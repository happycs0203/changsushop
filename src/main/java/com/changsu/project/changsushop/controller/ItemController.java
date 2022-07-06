package com.changsu.project.changsushop.controller;

import com.changsu.project.changsushop.controller.dto.ItemSearchCondition;
import com.changsu.project.changsushop.domain.item.Item;
import com.changsu.project.changsushop.repository.item.ItemQueryRepository;
import com.changsu.project.changsushop.service.ItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * @desc 상품 조회 컨트롤러
 * @author ChangSu, Ham
 * @version 1.0
 */
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    private final ItemQueryRepository itemQueryRepository;

    /**
     * @desc 상품 조회 로직
     * @param model
     * @param condition 상품 상세 조회 정보
     * @param pageable
     * @return
     */
    @GetMapping("/items")
    public String findAll(Model model,@ModelAttribute("itemSearch") ItemSearchCondition condition, Pageable pageable){
        Page<Item> items = itemQueryRepository.searchPageItems(condition, pageable);

        model.addAttribute("items", items);

        return "items/itemList";
    }

}
