package br.com.fiap.ez.fastfood.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import br.com.fiap.ez.fastfood.domain.model.User.UserType;


class UserTest {

    @Test
    void testUserConstructorAndGetters() {
        User user = new User("1", "12345678900", "John Doe", "john.doe@example.com", "password123", UserType.CLIENTE);
        assertEquals("1", user.getId());
        assertEquals("12345678900", user.getCpf());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals(UserType.CLIENTE, user.getUserType());
    }

    @Test
    void testUserSetters() {
        User user = new User();
        user.setId("2");
        user.setCpf("09876543211");
        user.setName("Jane Doe");
        user.setEmail("jane.doe@example.com");
        user.setPassword("password456");
        user.setUserType(UserType.FUNCIONARIO);

        assertEquals("2", user.getId());
        assertEquals("09876543211", user.getCpf());
        assertEquals("Jane Doe", user.getName());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("password456", user.getPassword());
        assertEquals(UserType.FUNCIONARIO, user.getUserType());
    }
}