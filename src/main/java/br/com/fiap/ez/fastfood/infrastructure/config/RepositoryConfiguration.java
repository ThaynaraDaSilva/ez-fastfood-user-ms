package br.com.fiap.ez.fastfood.infrastructure.config;

import br.com.fiap.ez.fastfood.adapters.out.repository.MongoUserRepository;
import br.com.fiap.ez.fastfood.adapters.out.repository.UserRepositoryImpl;
import br.com.fiap.ez.fastfood.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

	@Bean
	public UserRepository userRepository(MongoUserRepository mongoUserRepository) {
		return new UserRepositoryImpl(mongoUserRepository);
	}
}
