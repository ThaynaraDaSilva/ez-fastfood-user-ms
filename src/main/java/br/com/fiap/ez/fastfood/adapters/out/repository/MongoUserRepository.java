package br.com.fiap.ez.fastfood.adapters.out.repository;

import br.com.fiap.ez.fastfood.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface MongoUserRepository extends MongoRepository<User, String> {
	Optional<User> findByEmail(String email);
}
