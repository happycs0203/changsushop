package com.changsu.project.changsushop.repository.category;

import com.changsu.project.changsushop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @desc 카테고리 레포지토리
 * @author ChangSu, Ham
 * @version 1.0
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
