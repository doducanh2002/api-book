package org.aibles.book.bookservice.exception;

public class RunException extends RuntimeException {

    private int status = 0;
    private String message = "";

    public RunException() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
