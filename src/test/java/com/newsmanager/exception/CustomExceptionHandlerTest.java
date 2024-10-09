package com.newsmanager.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomExceptionHandlerTest {

    private final CustomExceptionHandler exceptionHandler = new CustomExceptionHandler();

    @Test
    void handleAccessDeniedException() {
        AccessDeniedException ex = new AccessDeniedException("Access Denied");

        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        WebRequest webRequest = new ServletWebRequest(mockRequest);

        ResponseEntity<String> response = exceptionHandler.handleAccessDeniedException(ex, webRequest);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals("Access is denied", response.getBody());
    }

    @Test
    void handleRuntimeExceptions() {
        RuntimeException ex = new RuntimeException("Runtime Exception");

        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        WebRequest webRequest = new ServletWebRequest(mockRequest);

        ResponseEntity<String> response = exceptionHandler.handleRuntimeExceptions(ex, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Runtime Exception", response.getBody());
    }

    @Test
    void handleAllExceptions() {
        Exception ex = new Exception("Test Exception");

        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        WebRequest webRequest = new ServletWebRequest(mockRequest);

        ResponseEntity<String> response = exceptionHandler.handleAllExceptions(ex, webRequest); // Указываем тип ResponseEntity<String>

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Test Exception", response.getBody());
    }
}
