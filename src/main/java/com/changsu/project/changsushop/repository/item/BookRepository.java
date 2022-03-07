package com.changsu.project.changsushop.repository.item;

import com.changsu.project.changsushop.domain.item.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
