package br.com.fiap.ez.fastfood.frameworks.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomExceptionHandlerTest {

    @InjectMocks
    private CustomExceptionHandler customExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleValidationExceptions() {
        // Create a mock BindingResult
        BindingResult bindingResult = mock(BindingResult.class);
        // Create a mock MethodArgumentNotValidException with the mocked BindingResult
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);

        // Set up the mock to return an error message
        ObjectError error = new ObjectError("productDTO", "Nome do produto é obrigatório");
        when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(error));

        ResponseEntity<ErrorResponse> response = customExceptionHandler.handleValidationExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Nome do produto é obrigatório; ", response.getBody().getMessage());
    }

    @Test
    void testHandleIllegalArgumentExceptions() {
        IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");

        ResponseEntity<ErrorResponse> response = customExceptionHandler.handleIllegalArgumentExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Invalid argument", response.getBody().getMessage());
    }

    @Test
    void testHandleBusinessException() {
        BusinessException exception = new BusinessException("Produto não pode ser excluído, pois já faz parte de pedidos.");

        ResponseEntity<Map<String, String>> response = customExceptionHandler.handleBusinessException(exception);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Produto não pode ser excluído, pois já faz parte de pedidos.", response.getBody().get("message"));
    }

    @Test
    void testHandleEntityNotFoundException() {
        EntityNotFoundException exception = new EntityNotFoundException("Entity not found");

        ResponseEntity<String> response = customExceptionHandler.handleEntityNotFoundException(exception);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Entity not found", response.getBody());
    }
}