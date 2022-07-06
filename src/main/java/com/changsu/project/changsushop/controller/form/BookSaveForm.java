package com.changsu.project.changsushop.controller.form;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @desc 교제 정보 저장 폼 
 * @author ChangSu, Ham
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class BookSaveForm {
    @NotBlank
    private String name;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    @NotNull
    @Max(value = 9999)
    private Integer stockQuantity;

    @NotBlank
    private String author;

    @NotBlank
    private String isbn;
}
