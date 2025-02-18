package org.aibles.book.bookservice.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AESResponseFilter implements Filter {
    private static final String AES_KEY = "YourSecretKey123"; // Key trùng với client

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        CustomHttpServletResponseWrapper responseWrapper = new CustomHttpServletResponseWrapper(httpResponse);

        chain.doFilter(request, responseWrapper); // Cho phép request đi tiếp

        if (httpResponse.getStatus() == HttpServletResponse.SC_OK) {
            try {
                String responseBody = responseWrapper.getCaptureAsString();
                String encryptedResponseBody = org.aibles.book.bookservice.filter.AESUtils.encrypt(responseBody, AES_KEY);

                httpResponse.setContentType("application/json");
                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.getOutputStream().write(encryptedResponseBody.getBytes());
                httpResponse.getOutputStream().flush();
            } catch (Exception e) {
                httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during response encryption");
            }
        }
    }
}
