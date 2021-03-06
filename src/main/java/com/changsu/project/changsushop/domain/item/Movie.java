package com.changsu.project.changsushop.domain.item;

import com.changsu.project.changsushop.controller.form.MovieSaveForm;
import com.changsu.project.changsushop.controller.form.MovieUpdateForm;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @desc 상품 - 영화 정보 엔티티
 * @author ChangSu, Ham
 * @version 1.0
 */
@Entity
@DiscriminatorValue("MOVIE")
@Getter @Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie extends Item {

    private String director; //감독
    private String actor; //배우

    public Movie(int price, String name, int stockQuantity,String director, String actor) {
        setPrice(price);
        setName(name);
        setStockQuantity(stockQuantity);
        setDirector(director);
        setActor(actor);
    }

    public Movie(MovieSaveForm movie) {
        setPrice(movie.getPrice());
        setName(movie.getName());
        setStockQuantity(movie.getStockQuantity());
        setDirector(movie.getDirector());
        setActor(movie.getActor());
    }

    public Movie(MovieUpdateForm form) {
        this.updateMovie(form);
    }

    public void updateMovie(MovieUpdateForm form){
        setPrice(form.getPrice());
        setName(form.getName());
        setStockQuantity(form.getStockQuantity());
        setDirector(form.getDirector());
        setActor(form.getActor());
    }
}
