package com.app.books.services;

import com.app.books.models.ApiResponse;
import com.app.books.models.Authors;
import com.app.books.models.Books;
import com.app.books.repositories.AuthorsRepository;
import com.app.books.repositories.BooksRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BooksServiceTest {
    @Mock
    private BooksRepository booksRepository;

    @Mock
    private AuthorsRepository authorsRepository;

    @InjectMocks
    private BooksService booksService;
    @Test
    void createBooks() {
        // Arrange
        Books inputBooks = new Books();
        Authors author = new Authors();
        author.setAuthor_id(1);
        inputBooks.setAuthors(author);

        when(authorsRepository.findById(1)).thenReturn(Optional.of(author));
        when(booksRepository.save(inputBooks)).thenReturn(inputBooks);

        // Act
        ResponseEntity<ApiResponse<Books>> responseEntity = booksService.createBooks(inputBooks);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("success", responseEntity.getBody().getStatus());
        assertEquals("libro creado con éxito", responseEntity.getBody().getMessage());
        assertEquals(inputBooks, responseEntity.getBody().getData());
    }

    @Test
    void getBooksById() {
        // Arrange
        Books expectedBook = new Books();
        when(booksRepository.findById(1)).thenReturn(Optional.of(expectedBook));

        // Act
        ResponseEntity<ApiResponse<Books>> responseEntity = booksService.getBooksById(1);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("success", responseEntity.getBody().getStatus());
        assertEquals("Libro encontrado", responseEntity.getBody().getMessage());
        assertEquals(expectedBook, responseEntity.getBody().getData());
    }

    @Test
    void getAllBooks() {
        // Arrange
        Pageable pageable = Pageable.unpaged();
        List<Books> booksList = new ArrayList<>();
        Page<Books> expectedPage = new PageImpl<>(booksList);
        when(booksRepository.findAll(pageable)).thenReturn(expectedPage);

        // Act
        Page<Books> resultPage = booksService.getAllBooks(pageable);

        // Assert
        assertEquals(expectedPage, resultPage);
    }

    @Test
    void filterBooks() {
        // Arrange
        List<Books> expectedBooksList = new ArrayList<>();
        when(booksRepository.findBooksByFilters(anyInt(), anyDouble(), anyDouble(), anyString())).thenReturn(expectedBooksList);

        // Act
        List<Books> resultBooksList = booksService.filterBooks(1, 0.0, 0.0, "genre");

        // Assert
        assertEquals(expectedBooksList, resultBooksList);
    }
}