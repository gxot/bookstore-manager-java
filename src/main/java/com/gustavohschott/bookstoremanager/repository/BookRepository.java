package com.gustavohschott.bookstoremanager.repository;

import com.gustavohschott.bookstoremanager.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
