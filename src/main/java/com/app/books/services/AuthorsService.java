package com.app.books.services;

import com.app.books.models.ApiResponse;
import com.app.books.models.Authors;
import com.app.books.repositories.AuthorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorsService {
    private final AuthorsRepository authorsRepository;

    public ResponseEntity<ApiResponse<Authors>> createAuthors(Authors authors) {
        try {
            // Guardar el autor en la base de datos
            Authors newAuthor = authorsRepository.save(authors);

            // Construir objeto ApiResponse con el autor creado
            ApiResponse<Authors> response = new ApiResponse<>(newAuthor, "success", "Autor creado correctamente");

            // Devolver el ResponseEntity con el ApiResponse
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, "error", "Error al crear autor: " + e.getMessage()));
        }
    }
}