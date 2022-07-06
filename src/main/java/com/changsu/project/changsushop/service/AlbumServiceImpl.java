package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.form.AlbumSaveForm;
import com.changsu.project.changsushop.controller.form.AlbumUpdateForm;
import com.changsu.project.changsushop.domain.item.Album;
import com.changsu.project.changsushop.repository.item.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @desc 앨범 서비스 인터페이스 구현
 * @author ChangSu, Ham
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService{

    private final AlbumRepository albumRepository;

    /**
     * @desc id에 맞는 앨범 조회
     * @param id
     * @return
     */
    @Override
    public Album findById(Long id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 앨범을 찾을 수 없습니다. id : " + id));
        return album;
    }

    /**
     * @dsec id에 맞는 앨범 조회 AlbumUpdateForm 으로 변환
     * @param id
     * @return
     */
    @Override
    public AlbumUpdateForm findByIdAUF(Long id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 앨범을 찾을 수 없습니다. id : " + id));
        return new AlbumUpdateForm(album);
    }

    /**
     * @desc 앨범 정보 저장
     * @param form
     * @return
     */
    @Override
    @Transactional
    public Long save(AlbumSaveForm form) {

        Album album = new Album(form);
        Album savedAlbum = albumRepository.save(album);

        return savedAlbum.getId() ;
    }

    /**
     * @desc 앨범 수정 (dirty checking 변경감지로 값을 변경)
     * @param form
     * @return
     */
    @Override
    @Transactional
    public Long update(AlbumUpdateForm form) {
        Album album = albumRepository.findById(form.getId()).orElseThrow(() -> new IllegalArgumentException("앨범을 찾을 수 없습니다. id : " + form.getId()));
        album.updateAlbum(form);
        return album.getId();
    }
}
