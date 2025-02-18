package org.aibles.book.bookservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.aibles.book.bookservice.dto.respone.BookResponse;
import org.aibles.book.bookservice.model.Book;
import org.aibles.book.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.aibles.book.bookservice.dto.request.BookRequest;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@Slf4j
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@RequestBody @Valid BookRequest bookRequest) {
//        if (bookRequest.getReleaseAt() == null) {
//            throw new IllegalArgumentException("Release date cannot be null");
//        }
        log.info("Create book request: {}", bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.createBook(bookRequest));
    }


    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable("id") int bookId,
                                                               @RequestBody BookRequest bookRequest){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(bookId, bookRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable("id") int bookId){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.deleteBook(bookId));
    }

    @GetMapping
    public ResponseEntity<List<Book>> listBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.listBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable("id") int bookId,
                                                    @RequestBody BookResponse bookResponse){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(bookId, bookResponse));
    }

}


