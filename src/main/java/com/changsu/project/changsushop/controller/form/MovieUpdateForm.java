package com.changsu.project.changsushop.controller.form;

import com.changsu.project.changsushop.domain.item.Movie;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @desc 영화 정보 수정 폼
 * @author ChangSu, Ham
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class MovieUpdateForm {
    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    //수정에서는 수량은 자유롭게 변경할 수 있다.
    private Integer stockQuantity;

    private String director;

    private String actor;

    public MovieUpdateForm(Long id, String name, Integer price, Integer stockQuantity, String director, String actor) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.director = director;
        this.actor = actor;
    }

    public MovieUpdateForm(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.price = movie.getPrice();
        this.stockQuantity = movie.getStockQuantity();
        this.director = movie.getDirector();
        this.actor = movie.getActor();
    }


}
