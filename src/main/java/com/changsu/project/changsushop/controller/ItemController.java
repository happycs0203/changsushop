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

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    private final ItemQueryRepository itemQueryRepository;

    @GetMapping("/items")
    public String findAll(Model model,@ModelAttribute("itemSearch") ItemSearchCondition condition, Pageable pageable){
        Page<Item> items = itemQueryRepository.searchPageItems(condition, pageable);

        model.addAttribute("items", items);

        return "items/itemList";
    }

}
