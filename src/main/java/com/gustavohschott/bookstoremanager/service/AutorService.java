package com.gustavohschott.bookstoremanager.service;

import com.gustavohschott.bookstoremanager.dto.BookDTO;
import com.gustavohschott.bookstoremanager.entity.Autor;
import com.gustavohschott.bookstoremanager.entity.Book;
import com.gustavohschott.bookstoremanager.exception.AutorNotFoundException;
import com.gustavohschott.bookstoremanager.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor findOrCreateAutor(BookDTO bookDTO) throws AutorNotFoundException {
        if (bookDTO.getAutor() == null) {
            throw new AutorNotFoundException("Autor não informado");
        }
        if (bookDTO.getAutor().getId() != null) {
            return autorRepository.findById(bookDTO.getAutor().getId())
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        } else if (bookDTO.getAutor().getNome() != null) {
            Autor novoAutor = new Autor();
            novoAutor.setNome(bookDTO.getAutor().getNome());
            novoAutor.setIdade(bookDTO.getAutor().getIdade());
            return autorRepository.save(novoAutor);
        } else {
            throw new RuntimeException("Dados incompletos");
        }
    }

    public Autor findOrCreateAutor(Book book) throws AutorNotFoundException {
        if (book.getAutor() == null) {
            throw new AutorNotFoundException("Autor não informado");
        }
        if (book.getAutor().getId() != null) {
            return autorRepository.findById(book.getAutor().getId())
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        } else if (book.getAutor().getNome() != null) {
            Autor novoAutor = new Autor();
            novoAutor.setNome(book.getAutor().getNome());
            novoAutor.setIdade(book.getAutor().getIdade());
            return autorRepository.save(novoAutor);
        } else {
            throw new RuntimeException("Dados incompletos");
        }
    }
}
