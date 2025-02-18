package org.aibles.book.bookservice.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
public class AESRequestFilter extends OncePerRequestFilter {
    private static final String AES_KEY = "YourSecretKey123"; // Key trùng với client

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)

            throws ServletException, IOException {
        log.info("(AESRequestFilter)request: {}, response:{}", request, response);
        if ("POST".equals(request.getMethod()) || "PUT".equals(request.getMethod())) {
            // Đọc request body gốc

            String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            log.info("requestBody: {}", requestBody);

            try {
                // Giải mã request body
                log.info("try doFilterInternal");

                String decryptedBody = AESUtils.decrypt(requestBody, AES_KEY);
                // Gửi request với dữ liệu đã giải mã
                HttpServletRequest wrappedRequest = new WrappedHttpServletRequest(request, decryptedBody);
                filterChain.doFilter(wrappedRequest, response);
                return;
            } catch (Exception e) {
                log.info("catch doFilterInternal");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid encrypted request body");
                return;
            }
        }

        // Nếu không phải POST hoặc PUT, tiếp tục request như bình thường
        filterChain.doFilter(request, response);
    }
}
