package br.com.fiap.ez.fastfood.frameworks.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.swagger.v3.oas.models.OpenAPI;

@SpringBootTest
public class OpenApiConfigTest {

    @Autowired
    private OpenAPI openAPI;

    @Test
    public void testCustomOpenApi() {
        // Assert that the OpenAPI bean is not null
        assertThat(openAPI).isNotNull();

        // Assert the properties of the OpenAPI object
        assertThat(openAPI.getInfo()).isNotNull();
        assertThat(openAPI.getInfo().getTitle()).isEqualTo("CATALOG MS");
        assertThat(openAPI.getInfo().getDescription()).isEqualTo("ez-fastfood-catalog-ms");
        assertThat(openAPI.getInfo().getVersion()).isEqualTo("1.0");
    }
}