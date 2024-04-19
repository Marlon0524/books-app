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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createBooks() {

    }


    @Test
    void getBooksById() {

    }

    @Test
    void getAllBooks() {
    }

    @Test
    void getBooksByFilters() {
    }
}