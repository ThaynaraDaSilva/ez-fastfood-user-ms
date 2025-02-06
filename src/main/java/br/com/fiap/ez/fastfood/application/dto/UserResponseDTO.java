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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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