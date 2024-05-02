package com.app.books.controllers;

import com.app.books.models.ApiResponse;
import com.app.books.models.Books;
import com.app.books.repositories.BooksRepository;
import com.app.books.services.BooksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BooksControllerTest {
    @Mock
    private BooksService booksService;

    @Mock
    private BooksRepository booksRepository;

    @InjectMocks
    private BooksController booksController;

    @Test
    void createBooks() {
        Books book = new Books();
        ApiResponse<Books> expectedResponse = new ApiResponse<>(book, "success", "Libro creado satisfactoriamente");
        when(booksService.createBooks(book)).thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        ResponseEntity<ApiResponse<Books>> actualResponse = booksController.createBooks(book);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(expectedResponse, actualResponse.getBody());
    }


    @Test
    void getBooksById() {
        Integer id = 1;
        Books book = new Books();
        ApiResponse<Books> expectedResponse = new ApiResponse<>(book, "success", "Libro obtenido satisfactoriamente");
        when(booksService.getBooksById(id)).thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        ResponseEntity<ApiResponse<Books>> actualResponse = booksController.getBooksById(id);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(expectedResponse, actualResponse.getBody());
    }

    @Test
    void getAllBooks() {
        int page = 0;
        int size = 10;
        List<Books> booksList = Arrays.asList(new Books(), new Books());
        Page<Books> booksPage = new PageImpl<>(booksList);
        ApiResponse<Page<Books>> expectedResponse = new ApiResponse<>(booksPage, "success", "Libros obtenidos satisfactoriamente");
        when(booksService.getAllBooks(PageRequest.of(page, size))).thenReturn(booksPage);

        ResponseEntity<ApiResponse<Page<Books>>> actualResponse = booksController.getAllBooks(page, size);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(expectedResponse, actualResponse.getBody());
    }

    @Test
    void getBooksByFilters() {
        Integer authorId = 1;
        Double minPrice = 10.0;
        Double maxPrice = 50.0;
        String gender = "Fiction";
        List<Books> booksList = Arrays.asList(new Books(), new Books());
        ApiResponse<List<Books>> expectedResponse = new ApiResponse<>(booksList, "success", "Libros obtenidos satisfactoriamente");
        when(booksRepository.findBooksByFilters(authorId, minPrice, maxPrice, gender)).thenReturn(booksList);

        ResponseEntity<ApiResponse<List<Books>>> actualResponse = booksController.getBooksByFilters(authorId, minPrice, maxPrice, gender);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(expectedResponse, actualResponse.getBody());
    }
}