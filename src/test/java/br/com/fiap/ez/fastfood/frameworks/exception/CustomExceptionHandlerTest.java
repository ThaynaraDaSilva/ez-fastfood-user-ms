package br.com.fiap.ez.fastfood.frameworks.exception;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomExceptionHandlerTest {

    private final CustomExceptionHandler exceptionHandler = new CustomExceptionHandler();

//    @Test
//    public void testHandleValidationExceptions() {
//        // Create a mock FieldError
//        FieldError fieldError = new FieldError("objectName", "fieldName", "Invalid value");
//        BindingResult bindingResult = mock(BindingResult.class);
//        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));
//
//        // Create a mock MethodArgumentNotValidException
//        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
//        when(exception.getBindingResult()).thenReturn(bindingResult);
//
//        // Call the exception handler
//        ResponseEntity<Map<String, String>> response = exceptionHandler.handleValidationExceptions(exception);
//
//        // Verify the response
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        Map<String, String> expectedResponse = new HashMap<>();
//        expectedResponse.put("fieldName", "Invalid value");
//        assertEquals(expectedResponse, response.getBody());
//    }

    @Test
    public void testHandleBusinessException() {
        String errorMessage = "Business error occurred";
        BusinessException exception = new BusinessException(errorMessage);

        // Call the exception handler
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleBusinessException(exception);

        // Verify the response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, String> expectedResponse = new HashMap<>();
        expectedResponse.put("message", errorMessage);
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testHandleIllegalArgumentExceptions() {
        String errorMessage = "Illegal argument error occurred";
        IllegalArgumentException exception = new IllegalArgumentException(errorMessage);

        // Call the exception handler
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleIllegalArgumentExceptions(exception);

        // Verify the response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, String> expectedResponse = new HashMap<>();
        expectedResponse.put("message", errorMessage);
        assertEquals(expectedResponse, response.getBody());
    }
}