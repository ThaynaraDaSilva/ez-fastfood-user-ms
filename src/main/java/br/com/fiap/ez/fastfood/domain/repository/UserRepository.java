package br.com.fiap.ez.fastfood.domain.repository;

import br.com.fiap.ez.fastfood.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
	User save(User user);
	Optional<User> findByEmail(String email);
	Optional<User> findByCpf(String cpf);
	List<User> findAll();
}
