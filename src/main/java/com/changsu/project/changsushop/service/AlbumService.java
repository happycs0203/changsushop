package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.form.AlbumSaveForm;
import com.changsu.project.changsushop.controller.form.AlbumUpdateForm;
import com.changsu.project.changsushop.domain.item.Album;

/**
 * @desc 앨범 서비스 인터페이스
 * @author ChangSu, Ham
 * @version 1.0
 */
public interface AlbumService {

    Album findById(Long id);

    AlbumUpdateForm findByIdAUF(Long id);

    Long save(AlbumSaveForm form);

    Long update(AlbumUpdateForm form);
}
