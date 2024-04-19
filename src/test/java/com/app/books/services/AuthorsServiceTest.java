package com.app.books.services;

import com.app.books.models.ApiResponse;
import com.app.books.models.Authors;
import com.app.books.repositories.AuthorsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorsServiceTest {
    @Mock
    private AuthorsRepository authorsRepository;
    @InjectMocks
    private AuthorsService authorsService;
    @Test
    void createAuthors() {
        // Arrange
        AuthorsRepository authorsRepository = mock(AuthorsRepository.class);
        AuthorsService authorsService = new AuthorsService(authorsRepository);
        Authors mockAuthors = new Authors(); // Mocking the Authors object
        Authors savedAuthor = new Authors(); // Mocking the saved author object

        // Mocking the repository method to return the saved author
        when(authorsRepository.save(any())).thenReturn(savedAuthor);

        // Act
        ResponseEntity<ApiResponse<Authors>> responseEntity = authorsService.createAuthors(mockAuthors);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("success", responseEntity.getBody().getStatus());
        assertEquals("Autor creado correctamente", responseEntity.getBody().getMessage());
        assertEquals(savedAuthor, responseEntity.getBody().getData());
    }
}