package com.example.bookzon.infrastructure.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")

public class BookController {


    @Tag(name = "get", description = "GET methods of hello APIs")
    @GetMapping("/hello")
    public ResponseEntity getHelloWorld() {

        return ResponseEntity.ok("HELLO WORLD!");
    }
}
