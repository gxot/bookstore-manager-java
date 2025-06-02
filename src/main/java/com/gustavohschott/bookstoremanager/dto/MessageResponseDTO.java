package com.gustavohschott.bookstoremanager.dto;

import com.gustavohschott.bookstoremanager.entity.Autor;
import com.gustavohschott.bookstoremanager.exception.AutorNotFoundException;
import com.gustavohschott.bookstoremanager.repository.AutorRepository;
import com.gustavohschott.bookstoremanager.repository.BookRepository;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Builder
public class MessageResponseDTO {
    private String message;
}
