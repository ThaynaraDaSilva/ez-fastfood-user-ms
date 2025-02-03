package br.com.fiap.ez.fastfood.infrastructure.mapper;

import br.com.fiap.ez.fastfood.infrastructure.persistence.ProductEntity;
import br.com.fiap.ez.fastfood.infrastructure.persistence.CategoryEntity; // Assuming you have a CategoryEntity class
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    @Test
    void testToDomain() {
        ProductDTO dto = new ProductDTO("Test Product", "A great product", 1L, 10.0);
        Product product = ProductMapper.toDomain(dto);

        assertNotNull(product);
        assertNull(product.getId()); // ID should be null since it's not set in DTO
        assertEquals("Test Product", product.getName());
        assertEquals("A great product", product.getDescription());
        assertEquals(10.0, product.getPrice());
    }

    @Test
    void testDomainToResponseDto() {
        Product product = new Product(1L, "Test Product", "A great product", 10.0, null);
        ProductResponseDTO dto = ProductMapper.domainToResponseDto(product);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Test Product", dto.getName());
        assertEquals("A great product", dto.getDescription());
        assertEquals(10.0, dto.getPrice());
        assertNull(dto.getCategoryName());
    }

    @Test
    void testEntityToDomain() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("Test Category");

        ProductEntity entity = new ProductEntity();
        entity.setId(1L);
        entity.setName("Test Product");
        entity.setDescription("A great product");
        entity.setPrice(10.0);
        entity.setCategory(categoryEntity);

        Product product = ProductMapper.entityToDomain(entity);

        assertNotNull(product);
        assertEquals(1L, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals("A great product", product.getDescription());
        assertEquals(10.0, product.getPrice());
        assertNotNull(product.getCategory());
        assertEquals(1L, product.getCategory().getId());
        assertEquals("Test Category", product.getCategory().getName());
    }

    @Test
    void testDomainToEntity() {
        Category category = new Category(1L, "Test Category");
        Product product = new Product(1L, "Test Product", "A great product", 10.0, category);

        ProductEntity entity = ProductMapper.domainToEntity(product);

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("Test Product", entity.getName());
        assertEquals("A great product", entity.getDescription());
        assertEquals(10.0, entity.getPrice());
        assertNotNull(entity.getCategory());
        assertEquals(1L, entity.getCategory().getId());
        assertEquals("Test Category", entity.getCategory().getName());
    }

    @Test
    void testOptionalToDomain() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setName("Test Category");

        ProductEntity entity = new ProductEntity();
        entity.setId(1L);
        entity.setName("Test Product");
        entity.setDescription("A great product");
        entity.setPrice(10.0);
        entity.setCategory(categoryEntity);

        Optional<ProductEntity> optionalEntity = Optional.of(entity);
        Product product = ProductMapper.optionalToDomain(optionalEntity);

        assertNotNull(product);
        assertEquals(1L, product.getId());
        assertNotNull(product.getCategory());
        assertEquals(1L, product.getCategory().getId());
    }

    @Test
    void testOptionalToEntity() {
        Category category = new Category(1L, "Test Category");
        Product product = new Product(1L, "Test Product", "A great product", 10.0, category);
        Optional<Product> optionalProduct = Optional.of(product);
        ProductEntity entity = ProductMapper.optionalToEntity(optionalProduct);

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("Test Product", entity.getName());
        assertNotNull(entity.getCategory());
        assertEquals(1L, entity.getCategory().getId());
        assertEquals("Test Category", entity.getCategory().getName());
    }
}