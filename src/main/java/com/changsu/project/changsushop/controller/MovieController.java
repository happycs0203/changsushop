package com.changsu.project.changsushop.controller;

import com.changsu.project.changsushop.controller.form.MovieSaveForm;
import com.changsu.project.changsushop.controller.form.MovieUpdateForm;
import com.changsu.project.changsushop.domain.item.Movie;
import com.changsu.project.changsushop.service.MovieService;
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

/**
 * @desc 상품 - 영화 저장 수정 조회 컨트롤러
 * @author ChangSu, Ham
 * @version 1.0
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    /**
     * @desc 영화 상세 정보 조회
     * @param movieId
     * @param model
     * @return
     */
    @GetMapping("/items/movies/{movieId}")
    public String movie(@PathVariable("movieId") Long movieId, Model model){
        Movie movie = movieService.findById(movieId);
        MovieUpdateForm movieUpdateForm = new MovieUpdateForm(movie);
        model.addAttribute("movie", movieUpdateForm);
        return "items/movies/movie";
    }

    /**
     * @desc 영화 생성 페이지 이동
     * @param model
     * @return
     */
    @GetMapping("/items/movies/new")
    public String createMovieForm(Model model) {
        model.addAttribute("movieForm", new MovieSaveForm());
        return "items/movies/createMovieForm";
    }

    /**
     * @desc 영화 생성 로직
     * @param form 영화 생성 정보
     * @param bindingResult 검증 객체
     * @param redirectAttributes 리다이렉트 데이터 매핑
     * @return
     */
    @PostMapping("/items/movies/new")
    public String createMovie(@Validated @ModelAttribute("movieForm") MovieSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.error("error{}", bindingResult);
            return "/items/movies/createMovieForm";
        }

        Long movieId = movieService.createMovie(form);

        redirectAttributes.addAttribute("movieId", movieId);

        return "redirect:/items/movies/{movieId}";
    }

    /**
     * @desc 영화 수정 페이지 이동
     * @param movieId
     * @param model
     * @return
     */
    @GetMapping("/items/movies/{movieId}/update")
    public String updateMovieForm(@PathVariable("movieId") Long movieId, Model model) {

        MovieUpdateForm movieForm = movieService.findByIdMUF(movieId);

        model.addAttribute("movieForm", movieForm);
        return "items/movies/updateMovieForm";
    }

    /**
     * @desc 영화 수정 로직
     * @param movieId
     * @param form 영화 수정 데이터
     * @param bindingResult 검증 객체
     * @param redirectAttributes 리다이렉트 데이터 매핑
     * @return
     */
    @PostMapping("/items/movies/{movieId}/update")
    public String updateMovie(@PathVariable("movieId") Long movieId, @Validated @ModelAttribute("movieForm") MovieUpdateForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("error{}", bindingResult);
            return "items/movies/updateMovieForm";
        }

        Long updateMovieId = movieService.updateMovie(form);

        redirectAttributes.addAttribute("movieId", updateMovieId);

        return "redirect:/items/movies/{movieId}";

    }



}
