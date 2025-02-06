package br.com.fiap.ez.fastfood.application.usecases;

import br.com.fiap.ez.fastfood.application.dto.UserRequestDTO;
import br.com.fiap.ez.fastfood.application.dto.UserResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.User;
import br.com.fiap.ez.fastfood.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;

//    public User registerUser(User user) {
//        return userRepository.save(user);
//    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setUserType(userRequestDTO.getUserType());

        User savedUser = userRepository.save(user);

        return toResponseDTO(savedUser);
    }

    private UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getUserType().name()
        );
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setUserType(user.getUserType().name());
        return dto;
    }
}
