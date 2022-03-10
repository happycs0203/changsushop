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

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemQueryRepository itemQueryRepository;

    private final OrderItemQueryRepository orderItemQueryRepository;

    @GetMapping("/orders")
    public String orders(@ModelAttribute("orderSearch") OrderSearchCondition condition, Model model, Pageable pageable) {

        Page<Order> orders = orderService.orderPageList(condition, pageable);

        model.addAttribute("orders", orders);
        return "orders/orderList";
    }

    @GetMapping("/orders/{orderId}")
    public String order(@PathVariable("orderId") Long orderId, Model model, Pageable pageable) {

        Page<OrderItemDto> orderItems = orderItemQueryRepository.orderItemPageList(orderId, pageable);

        OrderDto order = orderRepository.orderById(orderId);

        model.addAttribute("orderItems", orderItems);
        model.addAttribute("orderId", orderId);
        model.addAttribute("order", order);

        return "orders/orderItemList";
    }

    @GetMapping("/orders/memberList")
    public String ordersMemberList(@ModelAttribute("memberSearch") MemberSearchCondition condition, Model model,
                                   @PageableDefault(page = 0, size = 5, sort = "member_id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<MemberForm> members = memberRepository.searchPageByCondition(condition, pageable);

        model.addAttribute("members", members);
        return "orders/orderFormMemberList";
    }

    @GetMapping("/orders/itemList")
    public String ordersItemList(@ModelAttribute("itemSearch") ItemSearchCondition condition, Model model,
                                 @PageableDefault(page = 0, size = 5, sort = "item_id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Item> items = itemQueryRepository.searchPageItems(condition, pageable);

        model.addAttribute("items", items);
        return "orders/orderFormItemList";
    }

    @GetMapping("/orders/new")
    public String createForm(Model model) {
        return "orders/orderForm";
    }

    @PostMapping("/orders/new")
    public String create(@ModelAttribute("orderForm") OrderSaveForm form, RedirectAttributes redirectAttributes) {

        System.out.println(form.getMemberId());
        List<ItemSaveForm> items = form.getItems();
        Long orderId = orderService.save(form);

        redirectAttributes.addAttribute("orderId", orderId);
        return "redirect:/orders/{orderId}";

    }


    @PostMapping("/orders/{orderId}/cancel")
    @ResponseBody
    public String cancel(@PathVariable("orderId") Long orderId) {

        String result = orderService.cancel(orderId);

        return result;
    }

    

}
