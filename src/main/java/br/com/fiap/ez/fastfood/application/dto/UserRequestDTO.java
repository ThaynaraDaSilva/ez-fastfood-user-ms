package br.com.fiap.ez.fastfood.application.dto;

import br.com.fiap.ez.fastfood.application.validation.CPF;
import br.com.fiap.ez.fastfood.domain.model.User.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
	@NotBlank(message = "Nome não pode estar vazio")
	private String name;

	@Email(message = "E-mail inválido")
	@NotBlank(message = "E-mail não pode estar vazio")
	private String email;

	@NotBlank(message = "Senha não pode estar vazia")
	private String password;

	@CPF(message = "CPF inválido. Formato esperado: 123.456.789-10")
	@NotBlank(message = "CPF não pode estar vazio")
	private String cpf;
	private UserType userType; // "CLIENT" ou "EMPLOYEE"

}