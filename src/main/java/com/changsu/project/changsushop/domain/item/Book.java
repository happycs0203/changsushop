package com.changsu.project.changsushop.domain.item;

import com.changsu.project.changsushop.controller.form.BookSaveForm;
import com.changsu.project.changsushop.controller.form.BookUpdateForm;
import com.changsu.project.changsushop.service.BookService;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BOOK")
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Item{
    private String author;
    private String isbn;

    public Book(int price, String name, int stockQuantity,String author, String isbn) {
        setPrice(price);
        setName(name);
        setStockQuantity(stockQuantity);
        setIsbn(isbn);
        setAuthor(author);
    }

    public Book(BookSaveForm form) {
        setPrice(form.getPrice());
        setName(form.getName());
        setStockQuantity(form.getStockQuantity());
        setIsbn(form.getIsbn());
        setAuthor(form.getAuthor());
    }

    //    public static Book createBookByBookForm(BookForm form){
//        Book book = new Book();
//        book.setId(form.getId());
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        book.setAuthor(form.getAuthor());
//        book.setIsbn(form.getIsbn());
//
//        return book;
//    }

    public void changeBook(int price, String name, int stockQuantity, String isbn, String author) {
        setPrice(price);
        setName(name);
        setStockQuantity(stockQuantity);
        setIsbn(isbn);
        setAuthor(author);
    }

    public void updateBook(BookUpdateForm form) {
        setPrice(form.getPrice());
        setName(form.getName());
        setStockQuantity(form.getStockQuantity());
        setIsbn(form.getIsbn());
        setAuthor(form.getAuthor());
    }
}
