package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.dto.ItemDto;
import com.changsu.project.changsushop.domain.item.Item;

import java.util.List;

public interface ItemService {

    public List<Item> findAll();
}
