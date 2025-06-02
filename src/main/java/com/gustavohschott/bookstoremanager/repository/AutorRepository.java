package com.gustavohschott.bookstoremanager.repository;

import com.gustavohschott.bookstoremanager.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
