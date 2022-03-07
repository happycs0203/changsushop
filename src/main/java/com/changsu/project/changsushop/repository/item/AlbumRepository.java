package com.changsu.project.changsushop.repository.item;

import com.changsu.project.changsushop.domain.item.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
