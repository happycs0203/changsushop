package com.changsu.project.changsushop.controller.form;

import com.changsu.project.changsushop.domain.item.Book;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @desc 교제 정보 수정 폼
 * @author ChangSu, Ham
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class BookUpdateForm {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    //수정에서는 수량은 자유롭게 변경할 수 있다.
    private Integer stockQuantity;

    private String author;

    private String isbn;

    public BookUpdateForm(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.price = book.getPrice();
        this.stockQuantity = book.getStockQuantity();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
    }
}
