package com.gustavohschott.bookstoremanager.controller;

import com.gustavohschott.bookstoremanager.dto.BookDTO;
import com.gustavohschott.bookstoremanager.dto.MessageResponseDTO;
import com.gustavohschott.bookstoremanager.exception.AutorNotFoundException;
import com.gustavohschott.bookstoremanager.exception.BookNotFoundException;
import com.gustavohschott.bookstoremanager.exception.NoBooksFoundException;
import com.gustavohschott.bookstoremanager.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public MessageResponseDTO create(@RequestBody @Valid BookDTO bookDTO) throws AutorNotFoundException {
        return bookService.create(bookDTO);
    }

    @GetMapping("/{id}")
    public BookDTO findByID(@PathVariable Long id) throws BookNotFoundException {
        return bookService.findById(id);
    }

    @GetMapping("/allbooks")
    public List<BookDTO> findAll() throws NoBooksFoundException {
        return bookService.findAll();
    }

}
