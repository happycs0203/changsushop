package com.changsu.project.changsushop.repository.item;

import com.changsu.project.changsushop.domain.item.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
