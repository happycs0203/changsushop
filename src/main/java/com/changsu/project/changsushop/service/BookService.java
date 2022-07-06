package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.form.BookSaveForm;
import com.changsu.project.changsushop.controller.form.BookUpdateForm;
import com.changsu.project.changsushop.domain.item.Book;

/**
 * @desc 책 서비스 인터페이스
 * @author ChangSu, Ham
 * @version 1.0
 */
public interface BookService {

    Book findById(Long id);

    BookUpdateForm findByIdBUF(Long id);

    Long createBook(BookSaveForm form);

    Long updateBook(BookUpdateForm form);

}
