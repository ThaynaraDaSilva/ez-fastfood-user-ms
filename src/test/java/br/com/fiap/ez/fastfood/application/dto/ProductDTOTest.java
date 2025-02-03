package br.com.fiap.ez.fastfood.application.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProductDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidProductDTO() {
        ProductDTO productDTO = new ProductDTO("Test Product", "A great product", 1L, 10.0);
        Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
        assertTrue(violations.isEmpty(), "ProductDTO should be valid");
    }

    @Test
    void testInvalidProductDTO_NullName() {
        ProductDTO productDTO = new ProductDTO(null, "A great product", 1L, 10.0);
        Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
        assertFalse(violations.isEmpty(), "ProductDTO should be invalid due to null name");
        assertEquals("Nome do produto é obrigatório", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidProductDTO_EmptyName() {
        ProductDTO productDTO = new ProductDTO("", "A great product", 1L, 10.0);
        Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
        assertFalse(violations.isEmpty(), "ProductDTO should be invalid due to empty name");
        assertEquals("Nome do produto é obrigatório", violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidProductDTO_NegativePrice() {
        ProductDTO productDTO = new ProductDTO("Test Product", "A great product", 1L, -5.0);
        Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
        assertFalse(violations.isEmpty(), "ProductDTO should be invalid due to negative price");
        assertEquals("Preço deve ser positivo", violations.iterator().next().getMessage());
    }

    @Test
    void testValidProductDTO_ZeroPrice() {
        ProductDTO productDTO = new ProductDTO("Test Product", "A great product", 1L, 0.0);
        Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
        assertTrue(violations.isEmpty(), "ProductDTO should be valid with zero price");
    }
}