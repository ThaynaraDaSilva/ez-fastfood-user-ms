package br.com.fiap.ez.fastfood.infrastructure.persistence;

import br.com.fiap.ez.fastfood.domain.model.User.UserType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserEntityTest {

    @Test
    public void testUserEntityGettersAndSetters() {
        UserEntity userEntity = new UserEntity();

        // Set values
        userEntity.setId("1");
        userEntity.setCpf("123.456.789-10");
        userEntity.setName("John Doe");
        userEntity.setEmail("john.doe@example.com");
        userEntity.setPassword("securePassword123");
        userEntity.setUserType(UserType.CLIENTE);

        // Assert that the values are set correctly
        assertEquals("1", userEntity.getId());
        assertEquals("123.456.789-10", userEntity.getCpf());
        assertEquals("John Doe", userEntity.getName());
        assertEquals("john.doe@example.com", userEntity.getEmail());
        assertEquals("securePassword123", userEntity.getPassword());
        assertEquals(UserType.CLIENTE, userEntity.getUserType());
    }
}