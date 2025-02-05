package br.com.fiap.ez.fastfood.application.dto;

public class CreateUserDTO {
	private String name;
	private String email;
	private String cpf;
	private String userType; // "CLIENT" ou "EMPLOYEE"

	// Getters e Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}