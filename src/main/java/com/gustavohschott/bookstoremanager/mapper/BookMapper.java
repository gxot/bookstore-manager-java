package com.gustavohschott.bookstoremanager.mapper;

import com.gustavohschott.bookstoremanager.dto.BookDTO;
import com.gustavohschott.bookstoremanager.entity.Book;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "autor", ignore = true)
    Book toModel(BookDTO bookDTO);

    BookDTO toDTO(Book book);

    List<BookDTO> toDTO(List<Book> books);

    List<Book> toModel(List<BookDTO> bookDTOs);
}
