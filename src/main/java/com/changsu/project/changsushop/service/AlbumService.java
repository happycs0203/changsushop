package com.changsu.project.changsushop.service;

import com.changsu.project.changsushop.controller.form.AlbumSaveForm;
import com.changsu.project.changsushop.controller.form.AlbumUpdateForm;
import com.changsu.project.changsushop.domain.item.Album;

public interface AlbumService {

    public Album findById(Long id);

    public AlbumUpdateForm findByIdAUF(Long id);

    public Long save(AlbumSaveForm form);

    public Long update(AlbumUpdateForm form);
}
