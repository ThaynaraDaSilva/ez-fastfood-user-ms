package br.com.fiap.ez.fastfood.adapters.in.controller;

import br.com.fiap.ez.fastfood.application.dto.UserRequestDTO;
import br.com.fiap.ez.fastfood.application.dto.UserResponseDTO;
import br.com.fiap.ez.fastfood.application.usecases.UserUseCase;
import br.com.fiap.ez.fastfood.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userUseCase.registerUser(userRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> findUserByEmail(@PathVariable String email) {
        Optional<User> user = userUseCase.findByEmail(email);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
