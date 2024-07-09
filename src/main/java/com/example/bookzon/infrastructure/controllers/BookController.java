package com.example.bookzon.infrastructure.controllers;

import com.example.bookzon.application.usecases.book.SearchBookByTitleUseCase;
import com.example.bookzon.infrastructure.dtos.Book.ResponseSearchBookDTO;
import com.example.bookzon.infrastructure.utils.ApiResponse;
import io.micrometer.common.util.StringUtils;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;

@RestController
@RequestMapping("/api/book")
@Tag(name = "Book", description = "*")
public class BookController {

    private final SearchBookByTitleUseCase
            searchBookByTitleUseCase;

    public BookController(SearchBookByTitleUseCase searchBookByTitleUseCase) {
        this.searchBookByTitleUseCase = searchBookByTitleUseCase;
    }



    @GetMapping("/hello")
    public ResponseEntity getHelloWorld() {
        return ResponseEntity.ok("HELLO WORLD!");
    }
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<?>> searchByTitle(@RequestParam("title") String title) throws IOException {
        if (StringUtils.isBlank(title)) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(Collections.singletonList("Title must not be blank")));
        }
        ResponseSearchBookDTO result = searchBookByTitleUseCase.execute(title);
        return ResponseEntity.ok(new ApiResponse<>(result));
    }

}
