package br.com.fiap.ez.fastfood.adapters.in.controller;

import br.com.fiap.ez.fastfood.application.dto.UserRequestDTO;
import br.com.fiap.ez.fastfood.application.dto.UserResponseDTO;
import br.com.fiap.ez.fastfood.application.usecases.UserUseCase;
import br.com.fiap.ez.fastfood.domain.model.User;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserUseCase userUseCase;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser_Success() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setName("John Doe");
        userRequestDTO.setEmail("john.doe@example.com");
        userRequestDTO.setPassword("securePassword123");
        userRequestDTO.setCpf("123.456.789-10");
        userRequestDTO.setUserType(User.UserType.CLIENTE);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId("1");
        userResponseDTO.setName("John Doe");
        userResponseDTO.setEmail("john.doe@example.com");
        userResponseDTO.setCpf("123.456.789-10");
        userResponseDTO.setUserType(User.UserType.CLIENTE);

        when(userUseCase.registerUser (userRequestDTO)).thenReturn(userResponseDTO);

        ResponseEntity<UserResponseDTO> response = userController.registerUser (userRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponseDTO, response.getBody());

        verify(userUseCase).registerUser (userRequestDTO);
    }

    @Test
    public void testRegisterUser_BusinessException() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setCpf("123.456.789-10");

        when(userUseCase.registerUser (userRequestDTO)).thenThrow(new BusinessException("CPF já cadastrado"));

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userController.registerUser (userRequestDTO);
        });

        assertEquals("CPF já cadastrado", exception.getMessage());
        verify(userUseCase).registerUser (userRequestDTO);
    }

    @Test
    public void testFindUserByEmail_UserFound() {
        String email = "john.doe@example.com";
        User user = new User();
        user.setEmail(email);
        user.setName("John Doe");

        when(userUseCase.findByEmail(email)).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.findUserByEmail(email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());

        verify(userUseCase).findByEmail(email);
    }

    @Test
    public void testFindUserByEmail_UserNotFound() {
        String email = "nonexistent@example.com";

        when(userUseCase.findByEmail(email)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.findUserByEmail(email);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(userUseCase).findByEmail(email);
    }

    @Test
    public void testFindUserByCPF_Success() {
        String cpf = "123.456.789-10";
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setCpf(cpf);
        userResponseDTO.setName("John Doe");

        when(userUseCase.findUserByCPF(cpf)).thenReturn(userResponseDTO);

        ResponseEntity<UserResponseDTO> response = userController.findUserByCPF(cpf);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponseDTO, response.getBody());

        verify(userUseCase).findUserByCPF(cpf);
    }

    @Test
    public void testListAllUsers() {
        User user1 = new User();
        user1.setName("John Doe");
        User user2 = new User();
        user2.setName("Jane Doe");

        when(userUseCase.listAllUsers()).thenReturn(List.of(user1, user2));

        ResponseEntity<List<User>> response = userController.listAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("John Doe", response.getBody().get(0).getName());
        assertEquals("Jane Doe", response.getBody().get(1).getName());

        verify(userUseCase).listAllUsers();
    }

    @Test
    public void testListAllUsers_EmptyList() {
        when(userUseCase.listAllUsers()).thenReturn(Collections.emptyList());

        ResponseEntity<List<User>> response = userController.listAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());

        verify(userUseCase).listAllUsers();
    }
}