package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.form.AlbumSaveForm;
import com.changsu.project.changsushop.controller.form.AlbumUpdateForm;
import com.changsu.project.changsushop.domain.item.Album;
import com.changsu.project.changsushop.repository.item.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService{

    private final AlbumRepository albumRepository;

    @Override
    public Album findById(Long id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 앨범을 찾을 수 없습니다. id : " + id));
        return album;
    }

    @Override
    public AlbumUpdateForm findByIdAUF(Long id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 앨범을 찾을 수 없습니다. id : " + id));
        return new AlbumUpdateForm(album);
    }

    @Override
    @Transactional
    public Long save(AlbumSaveForm form) {

        Album album = new Album(form);
        Album savedAlbum = albumRepository.save(album);

        return savedAlbum.getId() ;
    }

    @Override
    @Transactional
    public Long update(AlbumUpdateForm form) {
        Album album = albumRepository.findById(form.getId()).orElseThrow(() -> new IllegalArgumentException("앨범을 찾을 수 없습니다. id : " + form.getId()));
        album.updateAlbum(form);
        return album.getId();
    }
}
