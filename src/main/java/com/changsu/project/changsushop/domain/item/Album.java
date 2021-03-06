package com.changsu.project.changsushop.domain.item;

import com.changsu.project.changsushop.controller.form.AlbumSaveForm;
import com.changsu.project.changsushop.controller.form.AlbumUpdateForm;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @desc 상품 - 앨범 정보 엔티티
 * @author ChangSu, Ham
 * @version 1.0
 */
@Entity
@DiscriminatorValue("ALBUM")
@Getter @Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED )
public class Album extends Item{
    private String artist; //가수
    private String etc; //기타정보

    public Album(int price, String name, int stockQuantity,String artist, String etc) {
        setPrice(price);
        setName(name);
        setStockQuantity(stockQuantity);
        setArtist(artist);
        setEtc(etc);
    }

    public Album(AlbumSaveForm form){
        setName(form.getName());
        setPrice(form.getPrice());
        setStockQuantity(form.getStockQuantity());
        setArtist(form.getArtist());
        setEtc(form.getEtc());
    }

    public void updateAlbum(AlbumUpdateForm form){
        setName(form.getName());
        setPrice(form.getPrice());
        setStockQuantity(form.getStockQuantity());
        setArtist(form.getArtist());
        setEtc(form.getEtc());
    }


}
