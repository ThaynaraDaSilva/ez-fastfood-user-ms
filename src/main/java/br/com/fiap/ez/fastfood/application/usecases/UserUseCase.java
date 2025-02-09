package br.com.fiap.ez.fastfood.application.usecases;

import java.util.List;
import java.util.Optional;

import br.com.fiap.ez.fastfood.application.dto.UserRequestDTO;
import br.com.fiap.ez.fastfood.application.dto.UserResponseDTO;
import br.com.fiap.ez.fastfood.domain.model.User;
import br.com.fiap.ez.fastfood.domain.repository.UserRepository;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;


public class UserUseCase {

    private final UserRepository userRepository;
    
    public UserUseCase(UserRepository userRepository) {
    	this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) {
        if (userRepository.findByCpf(userRequestDTO.getCpf()).isPresent()) {
            throw new BusinessException("CPF já cadastrado");
        }
        if (userRepository.findByEmail(userRequestDTO.getEmail()).isPresent()) {
            throw new BusinessException("E-mail já cadastrado");
        }

        User user = new User();
        user.setCpf(userRequestDTO.getCpf());
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setUserType(userRequestDTO.getUserType());

        User savedUser = userRepository.save(user);

        return toResponseDTO(savedUser);
    }

    public UserResponseDTO findUserByCPF(String cpf) {
        validateCPF(cpf);

        Optional<User> user = userRepository.findByCpf(cpf);
        if (user.isEmpty()) {
            throw new BusinessException("Usuário com CPF " + cpf + " não encontrado.");
        }

        return toResponseDTO(user.get());
    }

    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    private void validateCPF(String cpf) {
        if (cpf == null || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            throw new BusinessException("CPF inválido. O formato correto é XXX.XXX.XXX-XX.");
        }
    }

    private UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setCpf(user.getCpf());
        dto.setUserType(user.getUserType());
        return dto;
    }
}
