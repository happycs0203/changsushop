package com.changsu.project.changsushop.controller.form;

import com.changsu.project.changsushop.domain.item.Album;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @desc 앨범 정보 수정 폼 
 * @author ChangSu, Ham
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class AlbumUpdateForm {
    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private int price;

    //수정에서는 수량은 자유롭게 변경할 수 있다.
    private int stockQuantity;

    private String artist;

    private String etc;

    public AlbumUpdateForm(Album album) {
        this.id = album.getId();
        this.name = album.getName();
        this.price = album.getPrice();
        this.stockQuantity = album.getStockQuantity();
        this.artist = album.getArtist();
        this.etc = album.getEtc();
    }
}
