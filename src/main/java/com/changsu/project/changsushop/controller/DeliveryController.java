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

/**
 * @desc 배송 조회, 배송상태수정 컨트롤러
 * @author ChangSu, Ham
 * @version 1.0
 */
@Controller
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryQueryRepository deliveryQueryRepository;
    private final DeliveryService deliveryService;

    /**
     * @desc 배송 관련 정보 조회
     * @param model
     * @param condition 배송 상세 조회 정보
     * @param pageable
     * @return
     */
    @GetMapping("/deliveries")
    public String deliveries(Model model, @ModelAttribute("deliverySearch") DeliverySearchCondition condition, Pageable pageable) {


        Page<Delivery> deliveries = deliveryQueryRepository.searchDeliveryList(condition, pageable);

        model.addAttribute("deliveries", deliveries);

        return "deliveries/deliveryList";
    }

    /**
     * @desc 배송 정보 변경
     * @param deliveries 여러개의 배송 아이디
     * @return
     */
    @PostMapping("/deliveries/changeStatus")
    @ResponseBody
    public String changeDeliveriesStatus(@RequestParam("deliveries[]") List<Long> deliveries){

        String result = deliveryService.changeStatus(deliveries);
        System.out.println("result = " + result);
        return result;
    }

    /**
     * @desc 배송 정보 수정
     * @param deliveryId 배송 아이디
     * @param condition 배송 상태
     * @return
     */
    @PostMapping("/deliveries/{deliveryId}/changeStatus")
    @ResponseBody
    public String changeDeliveryStatus(@PathVariable("deliveryId") Long deliveryId, @ModelAttribute("delivery") DeliveryModifyCondition condition) {


        String result = deliveryService.changeStatusOne(condition);

        return result;
    }
}
