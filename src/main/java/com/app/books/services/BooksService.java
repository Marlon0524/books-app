package com.app.books.services;

import com.app.books.models.ApiResponse;
import com.app.books.models.Authors;
import com.app.books.models.Books;
import com.app.books.repositories.AuthorsRepository;
import com.app.books.repositories.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BooksService {
    private final BooksRepository booksRepository;
    private final AuthorsRepository authorsRepository;

public ResponseEntity<ApiResponse<Books>> createBooks (Books books) {
    try {
        // Obtener el ID del autor desde el libro
        Integer authorId = books.getAuthors().getAuthor_id();

        //verificar si el autor existe en la base de datos
        Optional<Authors> existingAuthorsOpcional = authorsRepository.findById(authorId);
        if (existingAuthorsOpcional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(null, "error", "Autor no encontrado con el ID: " + authorId));
        }

        //obtener la instancia existente de author en la bd
        Authors existingAuthors = existingAuthorsOpcional.get();

        //asignar el autor existente al libro
        books.setAuthors(existingAuthors);

        //guardar el libro en la bd
        Books newBooks = booksRepository.save(books);

        // construir objecto apiresponse con el libro creado
        ApiResponse<Books> response = new ApiResponse<>(newBooks, "success", "libro creado con éxito");

        //devolver el response entity con el api response
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    } catch (Exception e) {
        //manejar cualquier exepcion que pueda ocurrir
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(null, "error","Error al crear libro: "+ e.getMessage()));
    }
}

    public ResponseEntity<ApiResponse<Books>> getBooksById(@PathVariable Integer id) {
        try {
            Optional<Books> optionalBooks = booksRepository.findById(id);
            if (optionalBooks.isPresent()) {
                Books book = optionalBooks.get();
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ApiResponse<>(book, "success", "Libro encontrado"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(null, "error", "Libro no encontrado con el ID: " + id));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, "error", "Error al obtener libro: " + e.getMessage()));
        }
    }
    public Page<Books> getAllBooks(Pageable pageable) {
        return booksRepository.findAll(pageable);
    }
    public List<Books> filterBooks(Integer authorId, Double minPrice, Double maxPrice, String gender) {
        return booksRepository.findBooksByFilters(authorId, minPrice, maxPrice, gender);
    }

}
