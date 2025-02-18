package org.aibles.book.bookservice.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class WrappedHttpServletRequest extends HttpServletRequestWrapper {
    private final String body;

    public WrappedHttpServletRequest(HttpServletRequest request, String body) {
        super(request);
        this.body = body;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new StringReader(body));
    }
}
