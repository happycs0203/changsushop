package com.changsu.project.changsushop.controller;

import com.changsu.project.changsushop.controller.dto.DeliveryModifyCondition;
import com.changsu.project.changsushop.controller.dto.DeliverySearchCondition;
import com.changsu.project.changsushop.domain.Delivery;
import com.changsu.project.changsushop.repository.delivery.DeliveryQueryRepository;
import com.changsu.project.changsushop.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryQueryRepository deliveryQueryRepository;
    private final DeliveryService deliveryService;

    @GetMapping("/deliveries")
    public String deliveries(Model model, @ModelAttribute("deliverySearch") DeliverySearchCondition condition, Pageable pageable) {


        Page<Delivery> deliveries = deliveryQueryRepository.searchDeliveryList(condition, pageable);

        model.addAttribute("deliveries", deliveries);

        return "deliveries/deliveryList";
    }

    @PostMapping("/deliveries/changeStatus")
    @ResponseBody
    public String changeDeliveriesStatus(@RequestParam("deliveries[]") List<Long> deliveries){

        String result = deliveryService.changeStatus(deliveries);
        System.out.println("result = " + result);
        return result;
    }

    @PostMapping("/deliveries/{deliveryId}/changeStatus")
    @ResponseBody
    public String changeDeliveryStatus(@PathVariable("deliveryId") Long deliveryId, @ModelAttribute("delivery") DeliveryModifyCondition condition) {


        String result = deliveryService.changeStatusOne(condition);

        return result;
    }
}
