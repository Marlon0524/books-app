package com.app.books.controllers;

import com.app.books.models.ApiResponse;
import com.app.books.models.Authors;
import com.app.books.repositories.AuthorsRepository;
import com.app.books.services.AuthorsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorsControllerTest {
    @Mock
    private AuthorsService authorsService;
    @Mock
    private AuthorsRepository authorsRepository;
    @InjectMocks
    private AuthorsController authorsController;
    @Test
    void createAuthors() {
        // Arrange
        AuthorsService authorsService = mock(AuthorsService.class);
        AuthorsController controller = new AuthorsController(authorsService);
        Authors mockAuthors = new Authors(); // Mocking the Authors object

        // Mocking the service method to return a success response
        when(authorsService.createAuthors(any())).thenReturn(ResponseEntity.ok(new ApiResponse<>(mockAuthors, "success", "Author created successfully")));

        // Act
        ResponseEntity<ApiResponse<Authors>> responseEntity = controller.createAuthors(mockAuthors);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("success", responseEntity.getBody().getStatus());
        assertEquals("Author created successfully", responseEntity.getBody().getMessage());
        assertEquals(mockAuthors, responseEntity.getBody().getData());

        // Verify that the service method was called once with the correct argument
        verify(authorsService, times(1)).createAuthors(mockAuthors);

    }
}