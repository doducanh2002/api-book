package org.aibles.book.bookservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aibles.book.bookservice.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.aibles.book.bookservice.converter.MappingHelper;
import org.aibles.book.bookservice.dto.request.BookRequest;
import org.aibles.book.bookservice.dto.respone.BookResponse;
import org.aibles.book.bookservice.model.Book;
import org.aibles.book.bookservice.repository.BookRepository;
import org.aibles.book.bookservice.service.BookService;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final MappingHelper mappingHelper;

    @Override
    public BookResponse createBook(BookRequest bookRequest) {
        log.info("(create)bookCreate: {}", bookRequest);
        Book book = mappingHelper.map(bookRequest, Book.class);
        book.setReleaseAt(Instant.now(Clock.systemDefaultZone()));
        return mappingHelper.map(bookRepository.save(book), BookResponse.class);
    }

    @Override
    public BookResponse updateBook(int bookId, BookRequest bookRequest) {
        log.info("(update)bookUpdate: {}", bookRequest);
        return bookRepository.findById(bookId)
                .map(book -> {
                    book.setName(bookRequest.getName());
                    book.setDescription(bookRequest.getDescription());
                    book.setReleaseAt(bookRequest.getReleaseAt());
                    return mappingHelper.map(bookRepository.save(book), BookResponse.class);
                })
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public BookResponse deleteBook(int bookId) {
        log.info("(delete)bookDelete");
        return bookRepository.findById(bookId)
                .map(book -> {
                    bookRepository.delete(book);
                    return mappingHelper.map(book, BookResponse.class);
                })
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public BookResponse getBookById(int bookId) {
        return bookRepository.findById(bookId)
                .map(book -> {
                    return mappingHelper.map(book, BookResponse.class);
                })
                .orElseThrow(NotFoundException::new);
    }

}