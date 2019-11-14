package com.parkson.security;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
//    @Override
//    public void commence(HttpServletRequest httpServletRequest,
//                         HttpServletResponse httpServletResponse,
//                         AuthenticationException e) throws IOException, ServletException {
//        logger.error("Responding with unauthorized error. Message - {}", e.getMessage());
//        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
//    }
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
        throws IOException, ServletException {
      // 401
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
    }

    @ExceptionHandler (value = {AccessDeniedException.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException {
      // 403
      response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization Failed : " + accessDeniedException.getMessage());
    }

    @ExceptionHandler (value = {Exception.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
        Exception exception) throws IOException {
       // 500
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error : " + exception.getMessage());
    }
}
