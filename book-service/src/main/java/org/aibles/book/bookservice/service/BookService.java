package org.aibles.book.bookservice.service;

import org.aibles.book.bookservice.dto.request.BookRequest;
import org.aibles.book.bookservice.dto.respone.BookResponse;
import org.aibles.book.bookservice.model.Book;

import java.util.List;

public interface BookService {

    BookResponse createBook(BookRequest bookRequest);
    BookResponse updateBook(int bookId, BookRequest bookRequest);
    BookResponse deleteBook(int bookId);
    BookResponse getBookById(int bookId);
    List<Book> listBooks();

}
