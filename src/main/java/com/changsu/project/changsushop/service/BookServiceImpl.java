package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.form.BookSaveForm;
import com.changsu.project.changsushop.controller.form.BookUpdateForm;
import com.changsu.project.changsushop.domain.item.Book;
import com.changsu.project.changsushop.repository.item.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Override
    public Book findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 책이 없습니다. id : " + id));
        return book;
    }

    @Override
    public BookUpdateForm findByIdBUF(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 책이 없습니다. id : " + id));
        return new BookUpdateForm(book);
    }

    @Override
    @Transactional
    public Long createBook(BookSaveForm form) {
        Book book = new Book(form);
        Book savedBook = bookRepository.save(book);
        return savedBook.getId();
    }

    @Override
    @Transactional
    public Long updateBook(BookUpdateForm form) {
        Book book = bookRepository.findById(form.getId()).orElseThrow(() -> new IllegalArgumentException("해당 책이 없습니다. id : " + form.getId()));
        book.updateBook(form);
        return book.getId();
    }
}
