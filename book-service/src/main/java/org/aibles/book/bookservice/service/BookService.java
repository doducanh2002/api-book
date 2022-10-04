package org.aibles.book.bookservice.service;

import org.aibles.book.bookservice.dto.request.BookRequest;
import org.aibles.book.bookservice.dto.respone.BookResponse;
import org.aibles.book.bookservice.model.Book;

import java.util.List;

public interface BookService {

    /**
     * create information of books
     * @param request - information of books
     * @return - information of books
     */

    BookResponse createBook(BookRequest bookRequest);

    /**
     * update book by id
     * @param id - id of the book to update
     * @param request - information of books to update
     * @return - information of books new
     */

    BookResponse updateBook(int bookId, BookRequest bookRequest);

    /**
     * delete book by id
     * @param id - id of the book to delete
     * @return - successful delete
     */

    BookResponse deleteBook(int bookId);

    /**
     * get all book on list
     * @return - list book
     */

    List<Book> listBooks();

    /**
     * get book by id
     * @param id - id of the book to get
     * @return - information of books
     */

    BookResponse getBookById(int bookId, BookResponse bookResponse);
}
