package com.gustavohschott.bookstoremanager.service;

import com.gustavohschott.bookstoremanager.dto.BookDTO;
import com.gustavohschott.bookstoremanager.dto.MessageResponseDTO;
import com.gustavohschott.bookstoremanager.entity.Autor;
import com.gustavohschott.bookstoremanager.entity.Book;
import com.gustavohschott.bookstoremanager.exception.AutorNotFoundException;
import com.gustavohschott.bookstoremanager.exception.BookNotFoundException;
import com.gustavohschott.bookstoremanager.exception.NoBooksFoundException;
import com.gustavohschott.bookstoremanager.mapper.BookMapper;
import com.gustavohschott.bookstoremanager.repository.AutorRepository;
import com.gustavohschott.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;
    private AutorRepository autorRepository;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Autowired
    public BookService(BookRepository bookRepository, AutorRepository autorRepository) {
        this.bookRepository = bookRepository;
        this.autorRepository = autorRepository;
    }



    public MessageResponseDTO create(BookDTO bookDTO) throws AutorNotFoundException {

        Autor autor;

        if (bookDTO.getAutor() == null) {
            throw new AutorNotFoundException("Autor não informado");
        }

        if (bookDTO.getAutor().getId() != null) {
            autor = autorRepository.findById(bookDTO.getAutor().getId())
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        } else if (bookDTO.getAutor().getNome() != null) {
            Autor novoAutor = new Autor();
            novoAutor.setNome(bookDTO.getAutor().getNome());
            novoAutor.setIdade(bookDTO.getAutor().getIdade());
            autor = autorRepository.save(novoAutor);
        } else {
            throw new RuntimeException("Dados incompletos");
        }

        Book booktoSave = bookMapper.toModel(bookDTO);

        booktoSave.setAutor(autor);

        Book savedBook = bookRepository.save(booktoSave);
        return MessageResponseDTO.builder()
                .message("Book created with ID: " + savedBook.getId())
                .build();
    }

    public BookDTO findById(Long id) throws BookNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return bookMapper.toDTO(book);
    }

    public List<BookDTO> findAll() throws NoBooksFoundException {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new NoBooksFoundException("Nenhum livro na database");
        }
        return bookMapper.toDTO(books);
    }
}
