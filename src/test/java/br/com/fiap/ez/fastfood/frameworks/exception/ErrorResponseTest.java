package br.com.fiap.ez.fastfood.frameworks.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorResponseTest {

    @Test
    public void testConstructorAndGetter() {
        String errorMessage = "An error occurred";
        ErrorResponse errorResponse = new ErrorResponse(errorMessage);

        // Verify that the message is set correctly via the constructor
        assertEquals(errorMessage, errorResponse.getMessage(), "The message should match the constructor input");
    }

    @Test
    public void testSetter() {
        ErrorResponse errorResponse = new ErrorResponse("Initial message");

        String newMessage = "Updated error message";
        errorResponse.setMessage(newMessage);

        // Verify that the message is updated correctly
        assertEquals(newMessage, errorResponse.getMessage(), "The message should be updated correctly");
    }
}