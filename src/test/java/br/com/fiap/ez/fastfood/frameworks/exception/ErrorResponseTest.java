package br.com.fiap.ez.fastfood.frameworks.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void testErrorResponseConstructor() {
        String errorMessage = "An error occurred";
        ErrorResponse errorResponse = new ErrorResponse(errorMessage);

        assertEquals(errorMessage, errorResponse.getMessage(), "Error message should match the constructor input");
    }

    @Test
    void testGetMessage() {
        String errorMessage = "Another error occurred";
        ErrorResponse errorResponse = new ErrorResponse(errorMessage);

        assertEquals(errorMessage, errorResponse.getMessage(), "getMessage should return the correct error message");
    }

    @Test
    void testSetMessage() {
        ErrorResponse errorResponse = new ErrorResponse("Initial error message");
        String newMessage = "Updated error message";

        errorResponse.setMessage(newMessage);

        assertEquals(newMessage, errorResponse.getMessage(), "setMessage should update the error message");
    }
}