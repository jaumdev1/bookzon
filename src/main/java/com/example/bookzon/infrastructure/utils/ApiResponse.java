package com.example.bookzon.infrastructure.utils;

import java.util.List;

public class ApiResponse<T> {
    private T data;
    private List<String> errors;

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(List<String> errors) {
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public List<String> getErrors() {
        return errors;
    }
}