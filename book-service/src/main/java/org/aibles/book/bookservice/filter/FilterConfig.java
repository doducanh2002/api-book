package org.aibles.book.bookservice.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AESRequestFilter> requestFilter() {
        FilterRegistrationBean<AESRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AESRequestFilter());
        registrationBean.addUrlPatterns("/api/v1/books"); // Áp dụng cho API mong muốn
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AESResponseFilter> responseFilter() {
        FilterRegistrationBean<AESResponseFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AESResponseFilter());
        registrationBean.addUrlPatterns("/api/v1/books"); // Áp dụng cho API mong muốn
        return registrationBean;
    }
}
