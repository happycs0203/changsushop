package com.changsu.project.changsushop.controller;

import com.changsu.project.changsushop.controller.dto.OrderSearchCondition;
import com.changsu.project.changsushop.controller.form.ItemSaveForm;
import com.changsu.project.changsushop.controller.form.MemberSaveForm;
import com.changsu.project.changsushop.controller.form.OrderSaveForm;
import com.changsu.project.changsushop.domain.Order;
import com.changsu.project.changsushop.domain.OrderStatus;
import com.changsu.project.changsushop.domain.item.Book;
import com.changsu.project.changsushop.domain.item.Movie;
import com.changsu.project.changsushop.repository.item.BookRepository;
import com.changsu.project.changsushop.repository.item.MovieRepository;
import com.changsu.project.changsushop.service.MemberService;
import com.changsu.project.changsushop.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderControllerTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    EntityManager em;
    @Autowired
    OrderController orderController;

    @Test
    public void orderPageTest() throws Exception{
        MemberSaveForm memberSaveForm = MemberSaveForm.builder()
                .name("함창수")
                .password("Hamchangsu020#")
                .email("happycs0203@naver.com")
                .address("서울시")
                .addressDetail("목동서로 130")
                .buildingName("목동4단지아파트")
                .zipcode("12345")
                .build();

        //when
        Long memberId = memberService.save(memberSaveForm);
        //given
        Book book = new Book(100000,"book1",100,"changsu","1234");
        Movie movie = new Movie(100000,"Movie1",100,"changsu","1234");
        Movie savedMovie = movieRepository.save(movie);
        Book savedBook = bookRepository.save(book);

        OrderSaveForm orderSaveForm = new OrderSaveForm();
        orderSaveForm.setMemberId(memberId);

        List<ItemSaveForm> itemSaveForms = new ArrayList<>();
        ItemSaveForm itemSaveForm1 = new ItemSaveForm();
        itemSaveForm1.setItemId(savedMovie.getId());
        itemSaveForm1.setCount(10);
        itemSaveForms.add(itemSaveForm1);
        ItemSaveForm itemSaveForm2 = new ItemSaveForm();
        itemSaveForm2.setItemId(savedBook.getId());
        itemSaveForm2.setCount(5);
        itemSaveForms.add(itemSaveForm2);

        orderSaveForm.setItems(itemSaveForms);
        //when
        Long save = orderService.save(orderSaveForm);
        em.flush();
        em.clear();
        //then
        OrderSearchCondition condition = new OrderSearchCondition();
        condition.setOrderStatus(OrderStatus.ORDER);
        condition.setMemberName("함창수");
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<Order> orders = orderService.orderPageList(condition, pageRequest);
        for (Order order : orders.getContent()) {
            System.out.println("order.getId() = " + order.getId());
            System.out.println("order.getMember().getName() = " + order.getMember().getName());
            System.out.println("order.getOrderItems() = " + order.getOrderItems());
            System.out.println("order.getOrderItems().get(0).getItem().getName() = " + order.getOrderItems().get(0).getItem().getName());
        }

    }

}