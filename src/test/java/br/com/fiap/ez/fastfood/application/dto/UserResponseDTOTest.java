package br.com.fiap.ez.fastfood.application.dto;

import br.com.fiap.ez.fastfood.domain.model.User.UserType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserResponseDTOTest {

    @Test
    public void testUserResponseDTO() {
        // Create an instance of UserResponseDTO
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        // Set values
        userResponseDTO.setId("123");
        userResponseDTO.setName("John Doe");
        userResponseDTO.setEmail("john.doe@example.com");
        userResponseDTO.setCpf("123.456.789-10");
        userResponseDTO.setUserType(UserType.CLIENTE);

        // Assert that the values are set correctly
        assertEquals("123", userResponseDTO.getId());
        assertEquals("John Doe", userResponseDTO.getName());
        assertEquals("john.doe@example.com", userResponseDTO.getEmail());
        assertEquals("123.456.789-10", userResponseDTO.getCpf());
        assertEquals(UserType.CLIENTE, userResponseDTO.getUserType());
    }

    @Test
    public void testUserResponseDTOWithDifferentValues() {
        // Create another instance of UserResponseDTO
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId("456");
        userResponseDTO.setName("Jane Doe");
        userResponseDTO.setEmail("jane.doe@example.com");
        userResponseDTO.setCpf("987.654.321-00");
        userResponseDTO.setUserType(UserType.FUNCIONARIO);

        // Assert that the values are set correctly
        assertEquals("456", userResponseDTO.getId());
        assertEquals("Jane Doe", userResponseDTO.getName());
        assertEquals("jane.doe@example.com", userResponseDTO.getEmail());
        assertEquals("987.654.321-00", userResponseDTO.getCpf());
        assertEquals(UserType.FUNCIONARIO, userResponseDTO.getUserType());
    }
}