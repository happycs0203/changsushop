package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.form.MovieSaveForm;
import com.changsu.project.changsushop.controller.form.MovieUpdateForm;
import com.changsu.project.changsushop.domain.item.Movie;
import com.changsu.project.changsushop.repository.item.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;

    @Override
    public Movie findById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 영화가 없습니다. id : " + id));
        return movie;
    }

    @Override
    public MovieUpdateForm findByIdMUF(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 영화가 없습니다. id : " + id));
        return new MovieUpdateForm(movie);
    }

    @Override
    @Transactional
    public Long createMovie(MovieSaveForm form) {
        Movie movie = new Movie(form);
        Movie savedMovie = movieRepository.save(movie);
        return savedMovie.getId();
    }

    @Override
    @Transactional
    public Long updateMovie(MovieUpdateForm form) {
        Movie movie = movieRepository.findById(form.getId()).orElseThrow(() -> new IllegalArgumentException("해당 영화가 없습니다. id : " + form.getId()));
        movie.updateMovie(form);
        return movie.getId();
    }
}
