package com.changsu.project.changsushop.controller.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * @desc 모든 상품 정보 DTO
 * @author ChangSu, Ham
 * @version 1.0
 */
@Data
public class ItemDto {

    private Long id;

    private String name;

    private int price;

    private String dtype;

    private int stockQuantity;
    //===========Album 앨범===========
    private String artist;

    private String etc;
    //===========Book 책===========
    private String author;

    private String isbn;
    //===========Movie 영화===========
    private String director;

    private String actor;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private String createdBy;

    private String lastModifiedBy;

    @QueryProjection
    public ItemDto(Long id, String name, int price, String dtype, int stockQuantity, String artist, String etc, String author, String isbn, String director, String actor, LocalDateTime createdDate, LocalDateTime lastModifiedDate, String createdBy, String lastModifiedBy) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.dtype = dtype;
        this.stockQuantity = stockQuantity;
        this.artist = artist;
        this.etc = etc;
        this.author = author;
        this.isbn = isbn;
        this.director = director;
        this.actor = actor;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
    }
}
