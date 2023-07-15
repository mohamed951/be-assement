package com.assesment.fileuploadserviceapp.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

// For Simplicity, the app considering the request with Header Authorization not Empty/NULL will be authenticated
@Component
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authorizationHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (Objects.isNull(authorizationHeader) || authorizationHeader.isEmpty()) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User Is Not Authorized");
            return;
        }
        chain.doFilter(request, response);
    }
}
