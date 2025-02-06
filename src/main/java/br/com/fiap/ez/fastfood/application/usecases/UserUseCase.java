package br.com.fiap.ez.fastfood.application.usecases;

import br.com.fiap.ez.fastfood.domain.model.User;
import br.com.fiap.ez.fastfood.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
