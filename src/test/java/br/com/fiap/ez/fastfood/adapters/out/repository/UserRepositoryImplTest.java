package br.com.fiap.ez.fastfood.adapters.out.repository;

import br.com.fiap.ez.fastfood.domain.model.User;
import br.com.fiap.ez.fastfood.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserRepositoryImplTest {

    @Mock
    private MongoUserRepository mongoUserRepository;

    @InjectMocks
    private UserRepositoryImpl userRepositoryImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setId("1");
        user.setName("John Doe");

        when(mongoUserRepository.save(user)).thenReturn(user);

        User savedUser  = userRepositoryImpl.save(user);

        assertNotNull(savedUser );
        assertEquals("1", savedUser .getId());
        assertEquals("John Doe", savedUser .getName());

        verify(mongoUserRepository).save(user);
    }

    @Test
    public void testFindByEmail_UserFound() {
        String email = "john.doe@example.com";
        User user = new User();
        user.setEmail(email);
        user.setName("John Doe");

        when(mongoUserRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> foundUser  = userRepositoryImpl.findByEmail(email);

        assertTrue(foundUser .isPresent());
        assertEquals("John Doe", foundUser .get().getName());

        verify(mongoUserRepository).findByEmail(email);
    }

    @Test
    public void testFindByEmail_UserNotFound() {
        String email = "nonexistent@example.com";

        when(mongoUserRepository.findByEmail(email)).thenReturn(Optional.empty());

        Optional<User> foundUser  = userRepositoryImpl.findByEmail(email);

        assertFalse(foundUser .isPresent());

        verify(mongoUserRepository).findByEmail(email);
    }

    @Test
    public void testFindByCpf_UserFound() {
        String cpf = "123.456.789-10";
        User user = new User();
        user.setCpf(cpf);
        user.setName("John Doe");

        when(mongoUserRepository.findByCpf(cpf)).thenReturn(Optional.of(user));

        Optional<User> foundUser  = userRepositoryImpl.findByCpf(cpf);

        assertTrue(foundUser .isPresent());
        assertEquals("John Doe", foundUser .get().getName());

        verify(mongoUserRepository).findByCpf(cpf);
    }

    @Test
    public void testFindByCpf_UserNotFound() {
        String cpf = "987.654.321-00";

        when(mongoUserRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        Optional<User> foundUser  = userRepositoryImpl.findByCpf(cpf);

        assertFalse(foundUser .isPresent());

        verify(mongoUserRepository).findByCpf(cpf);
    }

    @Test
    public void testFindAll() {
        User user1 = new User();
        user1.setName("John Doe");
        User user2 = new User();
        user2.setName("Jane Doe");

        when(mongoUserRepository.findAll()).thenReturn(List.of(user1, user2));

        List<User> users = userRepositoryImpl.findAll();

        assertEquals(2, users.size());
        assertEquals("John Doe", users.get(0).getName());
        assertEquals("Jane Doe", users.get(1).getName());

        verify(mongoUserRepository).findAll();
    }

    @Test
    public void testFindAll_NoUsers() {
        when(mongoUserRepository.findAll()).thenReturn(Collections.emptyList());

        List<User> users = userRepositoryImpl.findAll();

        assertTrue(users.isEmpty());

        verify(mongoUserRepository).findAll();
    }
}