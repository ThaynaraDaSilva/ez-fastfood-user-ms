package br.com.fiap.ez.fastfood.application.dto;

import br.com.fiap.ez.fastfood.domain.model.User.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class UserRequestDTOTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testGettersAndSetters() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();

        // Set values
        userRequestDTO.setName("John Doe");
        userRequestDTO.setEmail("john.doe@example.com");
        userRequestDTO.setPassword("securePassword123");
        userRequestDTO.setCpf("123.456.789-10");
        userRequestDTO.setUserType(UserType.CLIENTE);

        // Assert that the values are set correctly
        assertEquals("John Doe", userRequestDTO.getName());
        assertEquals("john.doe@example.com", userRequestDTO.getEmail());
        assertEquals("securePassword123", userRequestDTO.getPassword());
        assertEquals("123.456.789-10", userRequestDTO.getCpf());
        assertEquals(UserType.CLIENTE, userRequestDTO.getUserType());
    }

    @Test
    public void testNameNotBlank() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setName(""); // This should trigger the NotBlank validation
        userRequestDTO.setEmail("john.doe@example.com");
        userRequestDTO.setPassword("securePassword123");
        userRequestDTO.setCpf("123.456.789-10");

        Set<ConstraintViolation<UserRequestDTO>> violations = validator.validate(userRequestDTO);
        assertEquals(1, violations.size(), "Should have one validation error for name");
        assertEquals("Nome não pode estar vazio", violations.iterator().next().getMessage());
    }

    // Additional tests for email, password, and CPF validations can be added here
}