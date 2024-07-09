package com.example.bookzon.infrastructure.googleapi;

import com.example.bookzon.domain.entities.Book;
import com.example.bookzon.infrastructure.dtos.Book.BookDTO;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonObjectParser;

import com.google.api.client.json.JsonParser;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.books.v1.Books;
import com.google.api.services.books.v1.BooksRequestInitializer;
import com.google.api.services.books.v1.model.Volume;
import com.google.api.services.books.v1.model.Volumes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoogleBooksService {

    private final Books booksService;

    @Value("${api.security.tokenGoogle.apikey}")
    private String apiKey;
    @Value("${api.security.tokenGoogle.appname}")
    private String appName;
    public GoogleBooksService() throws GeneralSecurityException, IOException {
        final JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        booksService = initializeBooksService(jsonFactory, apiKey);
    }

    private Books initializeBooksService(JsonFactory jsonFactory, String apiKey) throws GeneralSecurityException, IOException {

        return new Books.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                jsonFactory,
                null)
                .setApplicationName(appName)
                .setGoogleClientRequestInitializer(new BooksRequestInitializer(apiKey))
                .build();
    }

    public  List<BookDTO> searchBooks(String query) throws IOException {
       var volumes = booksService.volumes().list(query).execute();
        List<BookDTO> bookDTOS = volumes.getItems().stream()
                .map(volume -> {

                    String id = volume.getId();
                    String title = volume.getVolumeInfo().getTitle();
                    List<String> authors = volume.getVolumeInfo().getAuthors();
                    String author = authors != null ? authors.get(0) : null;
                    Integer publishedDate = volume.getVolumeInfo().getPublishedDate() != null ?
                            Integer.parseInt(volume.getVolumeInfo().getPublishedDate().substring(0, 4)) : null;
                    String description = volume.getVolumeInfo().getDescription();
                    List<String> categories = volume.getVolumeInfo().getCategories();
                    String genre = categories != null ? categories.get(0) : null;
                    String coverImage = volume.getVolumeInfo().getImageLinks() != null ?
                            volume.getVolumeInfo().getImageLinks().getThumbnail() : null;

                    return new BookDTO(id, title, author, publishedDate, description, genre, coverImage);
                })
                .collect(Collectors.toList());

        return bookDTOS;
    }
    public Book findBookById(String bookId) throws IOException {
        Volume volume = booksService.volumes().get(bookId).execute();

        String id = volume.getId();
        String title = volume.getVolumeInfo().getTitle();
        List<String> authors = volume.getVolumeInfo().getAuthors();
        String author = authors != null ? authors.get(0) : null;
        Integer publishedDate = volume.getVolumeInfo().getPublishedDate() != null ?
                Integer.parseInt(volume.getVolumeInfo().getPublishedDate().substring(0, 4)) : null;
        String description = volume.getVolumeInfo().getDescription();
        List<String> categories = volume.getVolumeInfo().getCategories();
        String genre = categories != null ? categories.get(0) : null;
        String coverImage = volume.getVolumeInfo().getImageLinks() != null ?
                volume.getVolumeInfo().getImageLinks().getThumbnail() : null;

        return new BookDTO(id, title, author, publishedDate, description, genre, coverImage).toEntity();
    }
}