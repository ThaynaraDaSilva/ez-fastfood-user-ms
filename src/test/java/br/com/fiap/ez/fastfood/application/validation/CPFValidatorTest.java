package br.com.fiap.ez.fastfood.application.validation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class CPFValidatorTest {

    private CPFValidator cpfValidator;
    private ConstraintValidatorContext context;

    @BeforeEach
    public void setUp() {
        cpfValidator = new CPFValidator();
        context = mock(ConstraintValidatorContext.class); // Mocking the context
    }

    @Test
    public void testValidCPF() {
        String validCpf = "123.456.789-10";
        assertTrue(cpfValidator.isValid(validCpf, context), "Valid CPF should return true");
    }

    @Test
    public void testInvalidCPFFormat() {
        String invalidCpf = "12345678910"; // No formatting
        assertFalse(cpfValidator.isValid(invalidCpf, context), "Invalid CPF format should return false");

        invalidCpf = "123.456.789-1"; // Incorrect check digit
        assertFalse(cpfValidator.isValid(invalidCpf, context), "Invalid CPF format should return false");

        invalidCpf = "123.456.78A-10"; // Contains a letter
        assertFalse(cpfValidator.isValid(invalidCpf, context), "Invalid CPF format should return false");
    }

    @Test
    public void testNullCPF() {
        assertFalse(cpfValidator.isValid(null, context), "Null CPF should return false");
    }
}