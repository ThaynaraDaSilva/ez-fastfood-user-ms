package br.com.fiap.ez.fastfood.adapters.in.controller;

import br.com.fiap.ez.fastfood.application.dto.UserRequestDTO;
import br.com.fiap.ez.fastfood.application.dto.UserResponseDTO;
import br.com.fiap.ez.fastfood.application.usecases.UserUseCase;
import br.com.fiap.ez.fastfood.domain.model.User;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
import br.com.fiap.ez.fastfood.frameworks.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Operations", description = "Operations related to users")
@RequiredArgsConstructor
public class UserController {

    private final UserUseCase userUseCase;

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity - Invalid CPF")
    })
    @PostMapping(path = "/create-new", produces = "application/json")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userUseCase.registerUser(userRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    @Hidden
    @Operation(summary = "Find user by Email")
    @GetMapping(path = "/find-by-email/{email}", produces = "application/json")
    public ResponseEntity<User> findUserByEmail(@PathVariable String email) {
        Optional<User> user = userUseCase.findByEmail(email);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleAllExceptions(Exception ex) {
//        // Log da exceção, se necessário
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body("Erro interno do servidor: " + ex.getMessage());
//    }

    @GetMapping("/find-by-document/{cpf}")
    public ResponseEntity<UserResponseDTO> findUserByCPF(@PathVariable String cpf) {
        UserResponseDTO response = userUseCase.findUserByCPF(cpf);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<User>> listAllUsers() {
        return ResponseEntity.ok(userUseCase.listAllUsers());
    }
}
