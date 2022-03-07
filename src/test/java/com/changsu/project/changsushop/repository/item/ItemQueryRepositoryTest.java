package com.changsu.project.changsushop.repository.item;

import com.changsu.project.changsushop.controller.dto.ItemSearchCondition;
import com.changsu.project.changsushop.domain.item.Album;
import com.changsu.project.changsushop.domain.item.Book;
import com.changsu.project.changsushop.domain.item.Item;
import com.changsu.project.changsushop.domain.item.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = true)
class ItemQueryRepositoryTest {

    @Autowired
    ItemQueryRepository itemQueryRepository;
    @Autowired
    EntityManager em;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    MovieRepository movieRepository;
    
    @Test
    public void itemTest() throws Exception{
        //given
        //given
        Book book = new Book(100000,"book1",100,"changsu","1234");
        Album album = new Album(100000,"album1",100,"changsu","1234");
        Movie movie1 = new Movie(100000,"movie1",100,"changsu","1234");
        Movie movie2 = new Movie(100000,"movie2",100,"changsu","1234");
        bookRepository.save(book);
        albumRepository.save(album);
        movieRepository.save(movie1);
        movieRepository.save(movie2);


        ItemSearchCondition condition = new ItemSearchCondition();
        condition.setDtype("MOVIE");
        condition.setSearchType("name");
        condition.setSearchText("movie2");


        List<Item> all = itemQueryRepository.search(condition);
        //when
        for (Item item : all) {
            System.out.println("item = " + item);
        }
        //then
    
    }

    @Test
    public void itemPageTest() throws Exception{
        //given
        //given
        Book book = new Book(100000,"book1",100,"changsu","1234");
        Album album = new Album(100000,"album1",100,"changsu","1234");
        Movie movie1 = new Movie(100000,"movie1",100,"changsu","1234");
        Movie movie2 = new Movie(100000,"movie2",100,"changsu","1234");
        bookRepository.save(book);
        albumRepository.save(album);
        movieRepository.save(movie1);
        movieRepository.save(movie2);


        ItemSearchCondition condition = new ItemSearchCondition();
//        condition.setDtype("MOVIE");
//        condition.setSearchType("name");
//        condition.setSearchText("movie2");

        PageRequest pageRequest = PageRequest.of(0, 4);


        Page<Item> items = itemQueryRepository.searchPageItems(condition, pageRequest);
        //when

        //then
        assertThat(items.getContent()).extracting("name").contains("movie2");

    }


}