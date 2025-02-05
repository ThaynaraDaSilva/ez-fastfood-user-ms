package br.com.fiap.ez.fastfood.application.dto;

public class UserResponseDTO {
	private String id;
	private String name;
	private String email;
	private String cpf;
	private String userType;

	public UserResponseDTO(String id, String name, String email, String cpf, String userType) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.userType = userType;
	}

	// Getters
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf() {
		return cpf;
	}

	public String getUserType() {
		return userType;
	}
}