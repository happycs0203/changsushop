package com.changsu.project.changsushop.controller.form;

import com.changsu.project.changsushop.domain.item.Movie;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class MovieSaveForm {

    @NotBlank
    private String name;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    @NotNull
    @Max(value = 9999)
    private Integer stockQuantity;

    @NotBlank
    private String director;

    @NotBlank
    private String actor;

}
