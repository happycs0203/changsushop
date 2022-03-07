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

@Slf4j
@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/items/movies/{movieId}")
    public String movie(@PathVariable("movieId") Long movieId, Model model){
        Movie movie = movieService.findById(movieId);
        MovieUpdateForm movieUpdateForm = new MovieUpdateForm(movie);
        model.addAttribute("movie", movieUpdateForm);
        return "items/movies/movie";
    }

    @GetMapping("/items/movies/new")
    public String createMovieForm(Model model) {
        model.addAttribute("movieForm", new MovieSaveForm());
        return "items/movies/createMovieForm";
    }

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

    @GetMapping("/items/movies/{movieId}/update")
    public String updateMovieForm(@PathVariable("movieId") Long movieId, Model model) {

        MovieUpdateForm movieForm = movieService.findByIdMUF(movieId);

        model.addAttribute("movieForm", movieForm);
        return "items/movies/updateMovieForm";
    }

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
