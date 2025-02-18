package org.aibles.book.bookservice.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AESResponseFilter implements Filter {
    private static final String AES_KEY = "YourSecretKey123"; // Key trùng với client

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("(AESResponseFilter)request: {}, response: {}", request, response);

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        CustomHttpServletResponseWrapper responseWrapper = new CustomHttpServletResponseWrapper(httpResponse);

        chain.doFilter(request, responseWrapper); // Cho phép request đi tiếp

        if (httpResponse.getStatus() == HttpServletResponse.SC_OK) {
            try {
                log.info("try doFilter");

                String responseBody = responseWrapper.getCaptureAsString();
                String encryptedResponseBody = org.aibles.book.bookservice.filter.AESUtils.encrypt(responseBody, AES_KEY);

                httpResponse.setContentType("application/json");
                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.getOutputStream().write(encryptedResponseBody.getBytes());
                httpResponse.getOutputStream().flush();
            } catch (Exception e) {
                log.info("try doFilter");

                httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during response encryption");
            }
        }
    }
}
