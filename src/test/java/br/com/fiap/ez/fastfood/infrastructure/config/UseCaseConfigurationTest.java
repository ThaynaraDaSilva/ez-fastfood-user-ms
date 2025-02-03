package br.com.fiap.ez.fastfood.infrastructure.config;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UseCaseConfigurationTest {

    @Configuration
    static class TestConfig {
        @Bean
        public ProductRepository productRepository() {
            return Mockito.mock(ProductRepository.class);
        }

        @Bean
        public CategoryRepository categoryRepository() {
            return Mockito.mock(CategoryRepository.class);
        }

        @Bean
        public ProductUseCase productUseCase(ProductRepository productRepository, CategoryRepository categoryRepository) {
            return new ProductUseCase(productRepository, categoryRepository);
        }
    }

    @Autowired
    private ProductUseCase productUseCase;

    @Test
    void testProductUseCaseBeanCreation() {
        assertNotNull(productUseCase, "ProductUseCase bean should not be null");
    }
}