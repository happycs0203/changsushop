package com.changsu.project.changsushop.repository.category;

import com.changsu.project.changsushop.domain.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class CategoryRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    CategoryRepository categoryRepository;
    
    @Test
    public void categoryTest() throws Exception{
        //given
        Category category = new Category("상품");
        Category category_sub = new Category("책");
        categoryRepository.save(category);
        categoryRepository.save(category_sub);

        category.addChildCategory(category_sub);
        
        //when

        List<Category> all = categoryRepository.findAll();

        //then
        Assertions.assertThat(all.get(1).getParent()).isEqualTo(category);
    
    }

}