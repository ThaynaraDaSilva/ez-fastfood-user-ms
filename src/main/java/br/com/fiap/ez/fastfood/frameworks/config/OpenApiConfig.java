package br.com.fiap.ez.fastfood.frameworks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
		@Bean
		public OpenAPI customOpenApi() {
			return new OpenAPI()
					.info(new Info()
					.title("USER MS")
					.description("ez-fastfood-user-ms")
					.version("1.0"));
					
		}
}
