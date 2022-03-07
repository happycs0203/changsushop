package com.changsu.project.changsushop.repository.item;

import com.changsu.project.changsushop.domain.item.Book;
import com.changsu.project.changsushop.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private BookRepository bookRepository;
    
    @Test
    public void bookRepositoryTest() throws Exception{
        //given
        Book book = new Book(100000,"book1",100,"changsu","1234");
        bookRepository.save(book);
        //when

        List<Book> books = bookRepository.findAll();

        for (Book book1 : books) {
            System.out.println("book1.getId() = " + book1.getId());
            System.out.println("book1.getIsbn() = " + book1.getIsbn());
            System.out.println("book1.getIsbn() = " + book1.getName());
        }

        //then
        assertThat(books.get(0)).isEqualTo(book);
        assertThat(books.size()).isEqualTo(1);
        assertThat(books).extracting("name").containsExactly("book1");
    
    }

}