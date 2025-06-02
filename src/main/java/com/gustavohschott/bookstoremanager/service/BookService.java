package com.gustavohschott.bookstoremanager.service;

import com.gustavohschott.bookstoremanager.dto.AutorDTO;
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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;
    private final AutorService autorService;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Autowired
    public BookService(BookRepository bookRepository, AutorService autorService) {
        this.bookRepository = bookRepository;
        this.autorService = autorService;
    }



    public MessageResponseDTO create(BookDTO bookDTO) throws AutorNotFoundException {

        Autor autor = autorService.findOrCreateAutor(bookDTO);

        Book booktoSave = bookMapper.toModel(bookDTO);

        booktoSave.setAutor(autor);

        Book savedBook = bookRepository.save(booktoSave);
        return MessageResponseDTO.builder()
                .message("Book created with ID: " + savedBook.getId())
                .build();
    }

    public MessageResponseDTO update(Long id, BookDTO bookDTO) throws BookNotFoundException, AutorNotFoundException {

        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        String bookNome = bookDTO.getNome();
        Integer bookPaginas = bookDTO.getPaginas();
        Integer bookCapitulos = bookDTO.getCapitulos();
        String bookISBN = bookDTO.getIsbn();
        String bookNomeEditora = bookDTO.getNomeEditora();
        AutorDTO autor = bookDTO.getAutor();

        if (bookNome != null) {
            book.setNome(bookNome);
        }
        if (bookPaginas != null) {
            book.setPaginas(bookPaginas);
        }
        if (bookCapitulos != null) {
            book.setCapitulos(bookCapitulos);
        }
        if (bookISBN != null) {
            book.setIsbn(bookISBN);
        }
        if (bookNomeEditora != null) {
            book.setNomeEditora(bookNomeEditora);
        }
        if (autor != null) {
            Autor autorToUpdate = autorService.findOrCreateAutor(bookDTO);
            book.setAutor(autorToUpdate);
        }
        Book savedBook = bookRepository.save(book);
        return MessageResponseDTO.builder()
                .message("Book updated with ID: " + savedBook.getId())
                .build();
    }

    public MessageResponseDTO delete(Long id) throws BookNotFoundException {
        Book bookToDelete = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        bookRepository.deleteById(id);
        return MessageResponseDTO.builder()
                .message("Book deleted with ID: " + bookToDelete.getId())
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
