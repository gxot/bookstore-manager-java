package com.gustavohschott.bookstoremanager.dto;

import com.gustavohschott.bookstoremanager.entity.Autor;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;

    @NotBlank
    @Size(max=200)
    private String nome;

    @NotNull
    private Integer paginas;

    @NotNull
    private Integer capitulos;

    @NotBlank
    @Size(max=100)
    private String isbn;

    @NotBlank
    @Size(max=200)
    private String nomeEditora;

    @Valid
    @NotNull
    private AutorDTO autor;
}
