package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.form.BookSaveForm;
import com.changsu.project.changsushop.controller.form.BookUpdateForm;
import com.changsu.project.changsushop.domain.item.Book;
import com.changsu.project.changsushop.repository.item.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @desc 책 서비스 구현
 * @author ChangSu, Ham
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    /**
     * @desc id로 책 조회
     * @param id
     * @return
     */
    @Override
    public Book findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 책이 없습니다. id : " + id));
        return book;
    }

    /**
     * @desc id로 책 조회 BookUpdateForm으로 리턴
     * @param id
     * @return
     */
    @Override
    public BookUpdateForm findByIdBUF(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 책이 없습니다. id : " + id));
        return new BookUpdateForm(book);
    }

    /**
     * @desc 책 생성
     * @param form
     * @return
     */
    @Override
    @Transactional
    public Long createBook(BookSaveForm form) {
        Book book = new Book(form);
        Book savedBook = bookRepository.save(book);
        return savedBook.getId();
    }

    /**
     * @desc 책 수정 (dirty checking 변겸감지)
     * @param form
     * @return
     */
    @Override
    @Transactional
    public Long updateBook(BookUpdateForm form) {
        Book book = bookRepository.findById(form.getId()).orElseThrow(() -> new IllegalArgumentException("해당 책이 없습니다. id : " + form.getId()));
        book.updateBook(form);
        return book.getId();
    }
}
