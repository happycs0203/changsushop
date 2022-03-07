package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.form.MovieSaveForm;
import com.changsu.project.changsushop.controller.form.MovieUpdateForm;
import com.changsu.project.changsushop.domain.item.Movie;

public interface MovieService {

    public Movie findById(Long id);

    public MovieUpdateForm findByIdMUF(Long id);

    public Long createMovie(MovieSaveForm form);

    public Long updateMovie(MovieUpdateForm form);
}
