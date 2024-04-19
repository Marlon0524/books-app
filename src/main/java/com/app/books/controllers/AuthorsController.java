package com.app.books.controllers;

import com.app.books.models.ApiResponse;
import com.app.books.models.Authors;
import com.app.books.services.AuthorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorsController {
    private final AuthorsService authorsService;

    @PostMapping
    public ResponseEntity<ApiResponse<Authors>> createAuthors(@RequestBody Authors authors) {
        return authorsService.createAuthors(authors);
    }
}
