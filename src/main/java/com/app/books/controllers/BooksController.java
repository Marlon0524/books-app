package com.app.books.controllers;

import com.app.books.models.ApiResponse;
import com.app.books.models.Books;
import com.app.books.repositories.BooksRepository;
import com.app.books.services.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
    private final BooksService booksService;
    private final BooksRepository booksRepository;
    private static final Logger logger = Logger.getLogger(BooksController.class.getName());
    @PostMapping
    public ResponseEntity<ApiResponse<Books>> createBooks(@RequestBody Books books) {
        return booksService.createBooks(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Books>> getBooksById(@PathVariable Integer id) {
        return booksService.getBooksById(id);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Books>>> getAllBooks(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        logger.info("Llamada al endpoint GET /books recibida.");
        Page<Books> booksPage = booksService.getAllBooks(PageRequest.of(page, size));

        if (booksPage != null && booksPage.hasContent()) {
            ApiResponse<Page<Books>> response = new ApiResponse<>(booksPage, "success", "Libros obtenidos satisfactoriamente");
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<Page<Books>> response = new ApiResponse<>(null, "error", "No se encontraron libros");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    @GetMapping("/filters")
    public ResponseEntity<ApiResponse<List<Books>>> getBooksByFilters(
            @RequestParam(required = false) Integer authorId,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String gender) {

        List<Books> books = booksRepository.findBooksByFilters(authorId, minPrice, maxPrice, gender);

        if (books.isEmpty()) {
            // Si la lista de libros está vacía, devuelve un ResponseEntity con un mensaje apropiado
            ApiResponse<List<Books>> response = new ApiResponse<>(null, "error", "No se encontraron libros con los filtros proporcionados");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            // Si se encontraron libros, devuelve un ResponseEntity con los libros y un mensaje de éxito
            ApiResponse<List<Books>> response = new ApiResponse<>(books, "success", "Libros obtenidos satisfactoriamente");
            return ResponseEntity.ok().body(response);
        }
    }

}
