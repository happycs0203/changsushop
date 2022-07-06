package com.changsu.project.changsushop.controller;

/**
 * @desc 상품 - 책 저장 수정 조회 컨트롤러
 * @author ChangSu, Ham
 * @version 1.0
 */
import com.changsu.project.changsushop.controller.form.BookSaveForm;
import com.changsu.project.changsushop.controller.form.BookUpdateForm;
import com.changsu.project.changsushop.domain.item.Book;
import com.changsu.project.changsushop.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    /**
     * @desc 책 정보 페이지 이동
     * @param bookId
     * @param model
     * @return
     */
    @GetMapping("/items/books/{bookId}")
    public String book(@PathVariable("bookId") Long bookId, Model model){
        Book findBook = bookService.findById(bookId);
        BookUpdateForm book = new BookUpdateForm(findBook);
        model.addAttribute("book", book);
        return "items/books/book";
    }

    /**
     * @desc 책 생성 페이지 이동
     * @param model
     * @return
     */
    @GetMapping("/items/books/new")
    public String createBookForm(Model model) {
        model.addAttribute("bookForm", new BookSaveForm());
        return "items/books/createBookForm";
    }

    /**
     * @desc 책 생성 로직
     * @param form 책 생성 객체
     * @param bindingResult 검증 객체
     * @param redirectAttributes 리다이렉트 데이터 매핑
     * @return
     */
    @PostMapping("/items/books/new")
    public String createBook(@Validated @ModelAttribute("bookForm") BookSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.error("error{}", bindingResult);
            return "items/books/createBookForm";
        }

        Long bookId = bookService.createBook(form);

        redirectAttributes.addAttribute("bookId", bookId);

        return "redirect:/items/books/{bookId}";
    }

    /**
     * @desc 교재 수정 페이지 이동
     * @param bookId
     * @param model
     * @return
     */
    @GetMapping("/items/books/{bookId}/update")
    public String updateBookForm(@PathVariable("bookId") Long bookId, Model model) {
        BookUpdateForm bookForm = bookService.findByIdBUF(bookId);
        model.addAttribute("bookForm", bookForm);
        return "items/books/updateBookForm";
    }

    /**
     * @desc 책 수정 로직
     * @param bookId 수정 교재 아이디
     * @param form 교제 수정 객체
     * @param bindingResult 검증 객체
     * @param redirectAttributes 리다이렉트 데이터 매핑
     * @return
     */
    @PostMapping("/items/books/{bookId}/update")
    public String updateBook(@PathVariable("bookId") Long bookId,@Validated @ModelAttribute("bookForm") BookUpdateForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("error{}", bindingResult);
            return "items/books/updateBookForm";
        }

        Long updateBookId = bookService.updateBook(form);

        redirectAttributes.addAttribute("bookId", updateBookId);

        return "redirect:/items/books/{bookId}";
    }
}
