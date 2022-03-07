package com.changsu.project.changsushop.repository.item;

import com.changsu.project.changsushop.domain.item.Album;
import com.changsu.project.changsushop.domain.item.Book;
import com.changsu.project.changsushop.domain.item.Item;
import com.changsu.project.changsushop.domain.item.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ItemRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void search() throws Exception{
        //given
        Book book = new Book(100000,"book1",100,"changsu","1234");
        Album album = new Album(100000,"album1",100,"changsu","1234");
        Movie movie = new Movie(100000,"movie1",100,"changsu","1234");
        bookRepository.save(book);
        albumRepository.save(album);
        movieRepository.save(movie);
        
        em.flush();
        em.clear();
        //when

        List<Item> all = itemRepository.findAll();

        for (Item item : all) {
            System.out.println("item = " + item.getDtype());
        }

        List<Book> all1 = bookRepository.findAll();
        for (Book book1 : all1) {
            System.out.println("book1 = " + book1);
        }
        


        //then

    }


}