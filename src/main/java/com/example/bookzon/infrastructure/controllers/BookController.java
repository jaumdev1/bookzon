package com.example.bookzon.infrastructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController {



    @GetMapping("/hello")
    public ResponseEntity getHelloWorld() {

        return ResponseEntity.ok("HELLO WORLD!");
    }
}
