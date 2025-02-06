package br.com.fiap.ez.fastfood.infrastructure.config;

import br.com.fiap.ez.fastfood.application.usecases.UserUseCase;
import br.com.fiap.ez.fastfood.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

	@Bean
	public UserUseCase userUseCase(UserRepository userRepository) {
		return new UserUseCase(userRepository);
	}
}