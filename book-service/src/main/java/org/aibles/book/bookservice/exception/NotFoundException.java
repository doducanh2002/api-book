package org.aibles.book.bookservice.exception;

public class NotFoundException extends RunException {

    public NotFoundException() {
        setStatus(404);
        setMessage("org.aibles.book.bookservice.exception.NotFoundException");
    }
}
