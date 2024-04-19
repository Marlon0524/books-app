package com.app.books.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Authors {
    @Id
    @GeneratedValue
    private Integer author_id;
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    private String name;
}
