package com.changsu.project.changsushop.controller;

import com.changsu.project.changsushop.controller.dto.*;
import com.changsu.project.changsushop.controller.form.ItemSaveForm;
import com.changsu.project.changsushop.controller.form.MemberForm;
import com.changsu.project.changsushop.controller.form.OrderSaveForm;
import com.changsu.project.changsushop.domain.Order;
import com.changsu.project.changsushop.domain.item.Item;
import com.changsu.project.changsushop.repository.item.ItemQueryRepository;
import com.changsu.project.changsushop.repository.item.ItemRepository;
import com.changsu.project.changsushop.repository.member.MemberRepository;
import com.changsu.project.changsushop.repository.order.OrderItemQueryRepository;
import com.changsu.project.changsushop.repository.order.OrderRepository;
import com.changsu.project.changsushop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @desc 주문 생성 수정 조회 컨트롤러
 * @author ChangSu, Ham
 * @version 1.0
 */
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemQueryRepository itemQueryRepository;

    private final OrderItemQueryRepository orderItemQueryRepository;

    /**
     * @desc 주문 조회 로직
     * @param condition
     * @param model
     * @param pageable
     * @return
     */
    @GetMapping("/orders")
    public String orders(@ModelAttribute("orderSearch") OrderSearchCondition condition, Model model, Pageable pageable) {

        Page<Order> orders = orderService.orderPageList(condition, pageable);

        model.addAttribute("orders", orders);
        return "orders/orderList";
    }

    /**
     * @desc 주문 상세 정보 조회
     * @param orderId
     * @param model
     * @param pageable
     * @return
     */
    @GetMapping("/orders/{orderId}")
    public String order(@PathVariable("orderId") Long orderId, Model model, Pageable pageable) {

        Page<OrderItemDto> orderItems = orderItemQueryRepository.orderItemPageList(orderId, pageable);

        OrderDto order = orderRepository.orderById(orderId);

        model.addAttribute("orderItems", orderItems);
        model.addAttribute("orderId", orderId);
        model.addAttribute("order", order);

        return "orders/orderItemList";
    }

    /**
     * @desc 상품 주문 회원리스트
     * @param condition 회원 검색 객체
     * @param model
     * @param pageable
     * @return
     */
    @GetMapping("/orders/memberList")
    public String ordersMemberList(@ModelAttribute("memberSearch") MemberSearchCondition condition, Model model,
                                   @PageableDefault(page = 0, size = 5, sort = "member_id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<MemberForm> members = memberRepository.searchPageByCondition(condition, pageable);

        model.addAttribute("members", members);
        return "orders/orderFormMemberList";
    }

    /**
     * @desc 상품 주문 상품리스트
     * @param condition 상품 검색 객체
     * @param model
     * @param pageable
     * @return
     */
    @GetMapping("/orders/itemList")
    public String ordersItemList(@ModelAttribute("itemSearch") ItemSearchCondition condition, Model model,
                                 @PageableDefault(page = 0, size = 5, sort = "item_id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Item> items = itemQueryRepository.searchPageItems(condition, pageable);

        model.addAttribute("items", items);
        return "orders/orderFormItemList";
    }

    /**
     * @desc 상품 주문 페이지 이동
     * @param model
     * @return
     */
    @GetMapping("/orders/new")
    public String createForm(Model model) {
        return "orders/orderForm";
    }

    /**
     * @desc 상품 주문 로직
     * @param form
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/orders/new")
    public String create(@ModelAttribute("orderForm") OrderSaveForm form, RedirectAttributes redirectAttributes) {

        System.out.println(form.getMemberId());
        List<ItemSaveForm> items = form.getItems();
        Long orderId = orderService.save(form);

        redirectAttributes.addAttribute("orderId", orderId);
        return "redirect:/orders/{orderId}";

    }


    /**
     * @desc 주문 취소 로직
     * @param orderId
     * @return
     */
    @PostMapping("/orders/{orderId}/cancel")
    @ResponseBody
    public String cancel(@PathVariable("orderId") Long orderId) {

        String result = orderService.cancel(orderId);

        return result;
    }

    

}
