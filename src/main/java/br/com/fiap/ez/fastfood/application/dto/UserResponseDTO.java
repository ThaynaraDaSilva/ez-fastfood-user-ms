package br.com.fiap.ez.fastfood.application.dto;

import br.com.fiap.ez.fastfood.domain.model.User.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
	private String id;
	private String name;
	private String email;
	private String cpf;
	private UserType userType;
}