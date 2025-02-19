package br.com.fiap.ez.fastfood.application.usecases;

import br.com.fiap.ez.fastfood.application.dto.UserRequestDTO;
import br.com.fiap.ez.fastfood.application.dto.UserResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.User;
import br.com.fiap.ez.fastfood.domain.repository.UserRepository;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserUseCase userUseCase;

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

        when(userRepository.findByCpf(userRequestDTO.getCpf())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(userRequestDTO.getEmail())).thenReturn(Optional.empty());

        User user = new User();
        user.setId("1");
        user.setCpf(userRequestDTO.getCpf());
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setUserType(userRequestDTO.getUserType());

        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO responseDTO = userUseCase.registerUser(userRequestDTO);

        assertNotNull(responseDTO);
        assertEquals("1", responseDTO.getId());
        assertEquals("John Doe", responseDTO.getName());
        assertEquals("john.doe@example.com", responseDTO.getEmail());
        assertEquals("123.456.789-10", responseDTO.getCpf());
        assertEquals(User.UserType.CLIENTE, responseDTO.getUserType());

        verify(userRepository).findByCpf(userRequestDTO.getCpf());
        verify(userRepository).findByEmail(userRequestDTO.getEmail());
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testRegisterUser_CpfAlreadyExists() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setCpf("123.456.789-10");

        when(userRepository.findByCpf(userRequestDTO.getCpf())).thenReturn(Optional.of(new User()));

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userUseCase.registerUser(userRequestDTO);
        });

        assertEquals("CPF já cadastrado", exception.getMessage());
        verify(userRepository).findByCpf(userRequestDTO.getCpf());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testRegisterUser_EmailAlreadyExists() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmail("john.doe@example.com");

        when(userRepository.findByCpf(userRequestDTO.getCpf())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(userRequestDTO.getEmail())).thenReturn(Optional.of(new User()));

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userUseCase.registerUser(userRequestDTO);
        });

        assertEquals("E-mail já cadastrado", exception.getMessage());
        verify(userRepository).findByEmail(userRequestDTO.getEmail());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testFindUserByCPF_Success() {
        String cpf = "123.456.789-10";
        User user = new User();
        user.setCpf(cpf);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        when(userRepository.findByCpf(cpf)).thenReturn(Optional.of(user));

        UserResponseDTO responseDTO = userUseCase.findUserByCPF(cpf);

        assertNotNull(responseDTO);
        assertEquals("John Doe", responseDTO.getName());
        assertEquals("john.doe@example.com", responseDTO.getEmail());
        assertEquals(cpf, responseDTO.getCpf());

        verify(userRepository).findByCpf(cpf);
    }

    @Test
    public void testFindUserByCPF_UserNotFound() {
        String cpf = "123.456.789-10";

        when(userRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userUseCase.findUserByCPF (cpf);
        });

        assertEquals("Usuário com CPF " + cpf + " não encontrado.", exception.getMessage());
        verify(userRepository).findByCpf(cpf);
    }

    @Test
    public void testListAllUsers() {
        User user1 = new User();
        user1.setName("John Doe");
        User user2 = new User();
        user2.setName("Jane Doe");

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<User> users = userUseCase.listAllUsers();

        assertEquals(2, users.size());
        assertEquals("John Doe", users.get(0).getName());
        assertEquals("Jane Doe", users.get(1).getName());

        verify(userRepository).findAll();
    }
}