package org.aibles.book.bookservice.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.aibles.book.bookservice.exception.RunException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.MessageSource;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class BookControllerAdvice {

    private final MessageSource messageSource;

    public BookControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(RunException.class)
    public ResponseEntity<Map<String, Object>> handle(
            RunException ex, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("status", ex.getStatus());
        errorMap.put("message", messageSource.getMessage(ex.getMessage(), null, locale));
        return ResponseEntity.status(HttpStatus.valueOf(ex.getStatus())).body(errorMap);
    }
}

