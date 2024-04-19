package com.app.books.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Books {
    @Id
    @GeneratedValue
    private Integer book_id;
    @Size(max = 100, message = "El titulo no puede tener más de 100 caracteres")
    private String title;
    @Size(max = 200, message = "La descripción no puede tener más de 200 caracteres")
    private String Description;
    private Double Price;
    private String Gender;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Authors authors;

    public void setId(Integer bookId) {
    }

    public Integer getId() {
        return book_id;
    }
}
