package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.form.MovieSaveForm;
import com.changsu.project.changsushop.controller.form.MovieUpdateForm;
import com.changsu.project.changsushop.domain.item.Movie;
import com.changsu.project.changsushop.repository.item.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @desc 영화 서비스 구현
 * @author ChangSu, Ham
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;

    /**
     * @desc id에 맞는 영화 조회
     * @param id
     * @return
     */
    @Override
    public Movie findById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 영화가 없습니다. id : " + id));
        return movie;
    }

    /**
     * @desc id에 맞는 영화 조회 MovieUpdateForm 리턴
     * @param id
     * @return
     */
    @Override
    public MovieUpdateForm findByIdMUF(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 영화가 없습니다. id : " + id));
        return new MovieUpdateForm(movie);
    }

    /**
     * @desc 영화 생성
     * @param form
     * @return
     */
    @Override
    @Transactional
    public Long createMovie(MovieSaveForm form) {
        Movie movie = new Movie(form);
        Movie savedMovie = movieRepository.save(movie);
        return savedMovie.getId();
    }

    /**
     * @desc 영화 수정 (dirty checking 변겸감지)
     * @param form
     * @return
     */
    @Override
    @Transactional
    public Long updateMovie(MovieUpdateForm form) {
        Movie movie = movieRepository.findById(form.getId()).orElseThrow(() -> new IllegalArgumentException("해당 영화가 없습니다. id : " + form.getId()));
        movie.updateMovie(form);
        return movie.getId();
    }
}
