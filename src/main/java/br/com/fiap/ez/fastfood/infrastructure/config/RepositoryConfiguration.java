package br.com.fiap.ez.fastfood.infrastructure.config;

import br.com.fiap.ez.fastfood.adapters.out.repository.CustomerRepositoryImpl;
import br.com.fiap.ez.fastfood.adapters.out.repository.JpaCustomerRepository;
import br.com.fiap.ez.fastfood.domain.repository.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RepositoryConfiguration {

	@Bean
	public CustomerRepository customerRepository(JpaCustomerRepository customerJpaRepository) {
		return new CustomerRepositoryImpl(customerJpaRepository);
	}

}
