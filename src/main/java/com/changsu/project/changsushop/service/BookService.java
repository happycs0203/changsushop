package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.form.BookSaveForm;
import com.changsu.project.changsushop.controller.form.BookUpdateForm;
import com.changsu.project.changsushop.domain.item.Book;

public interface BookService {

    public Book findById(Long id);

    public BookUpdateForm findByIdBUF(Long id);

    public Long createBook(BookSaveForm form);

    public Long updateBook(BookUpdateForm form);

}
