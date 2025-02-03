package br.com.fiap.ez.fastfood.adapters.out.repository;

import br.com.fiap.ez.fastfood.infrastructure.persistence.ProductEntity;
import br.com.fiap.ez.fastfood.infrastructure.persistence.CategoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductRepositoryImplTest {

    private JpaProductRepository jpaProductRepository;
    private ProductRepositoryImpl productRepository;

    @BeforeEach
    void setUp() {
        jpaProductRepository = mock(JpaProductRepository.class);
        productRepository = new ProductRepositoryImpl(jpaProductRepository);
    }

    private Category createCategory(Long id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        return category;
    }

    private CategoryEntity createCategoryEntity(Long id, String name) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(id);
        categoryEntity.setName(name);
        return categoryEntity;
    }

    private Product createProduct(Long id, String name, Double price, Category category) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        return product;
    }

    private ProductEntity createProductEntity(Long id, String name, Double price, CategoryEntity categoryEntity) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(id);
        productEntity.setName(name);
        productEntity.setPrice(price);
        productEntity.setCategory(categoryEntity);
        return productEntity;
    }

    @Test
    void testSaveWithNullId() {
        Category category = createCategory(1L, "Test Category");
        Product product = createProduct(null, "Test Product", 10.0, category);
        ProductEntity productEntity = createProductEntity(null, "Test Product", 10.0, new CategoryEntity());

        when(jpaProductRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct);
        assertNull(savedProduct.getId());
        verify(jpaProductRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testSaveWithExistingId() {
        Category category = createCategory(1L, "Test Category");
        Product product = createProduct(1L, "Test Product", 10.0, category);
        ProductEntity productEntity = createProductEntity(1L, "Test Product", 10.0, new CategoryEntity());

        when(jpaProductRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct);
        assertEquals(1L, savedProduct.getId());
        verify(jpaProductRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testUpdateWithNullId() {
        Category category = createCategory(1L, "Updated Category");
        Product product = createProduct(null, "Updated Product", 15.0, category);
        ProductEntity productEntity = createProductEntity(null, "Updated Product", 15.0, new CategoryEntity());

        when(jpaProductRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        Product updatedProduct = productRepository.update(product);

        assertNotNull(updatedProduct);
        assertNull(updatedProduct.getId());
        verify(jpaProductRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testUpdateWithExistingId() {
        Category category = createCategory(1L, "Updated Category");
        Product product = createProduct(1L, "Updated Product", 15.0, category);
        ProductEntity productEntity = createProductEntity(1L, "Updated Product", 15.0, new CategoryEntity());

        when(jpaProductRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        Product updatedProduct = productRepository.update(product);

        assertNotNull(updatedProduct);
        assertEquals(1L, updatedProduct.getId());
        verify(jpaProductRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testFindById() {
        CategoryEntity categoryEntity = createCategoryEntity(1L, "Test Category");
        ProductEntity productEntity = createProductEntity(1L, "Test Product", 10.0, categoryEntity);

        when(jpaProductRepository.findById(1L)).thenReturn(Optional.of(productEntity));

        Optional<Product> foundProduct = productRepository.findById(1L);

        assertTrue(foundProduct.isPresent());
        assertEquals(1L, foundProduct.get().getId());
        assertEquals(10.0, foundProduct.get().getPrice());
        assertEquals(1L, foundProduct.get().getCategory().getId());
        verify(jpaProductRepository, times(1)).findById(1L);
    }

    @Test
    void testFindProductById() {
        CategoryEntity categoryEntity = createCategoryEntity(1L, "Test Category");
        ProductEntity productEntity = createProductEntity(1L, "Test Product", 10.0, categoryEntity);

        when(jpaProductRepository.findProductById(1L)).thenReturn(productEntity);

        Product foundProduct = productRepository.findProductById(1L);

        assertNotNull(foundProduct);
        assertEquals(1L, foundProduct.getId());
        assertEquals(10.0, foundProduct.getPrice());
        assertEquals(1L, foundProduct.getCategory().getId());
        verify(jpaProductRepository, times(1)).findProductById(1L);
    }

    @Test
    void testFindAll() {
        CategoryEntity categoryEntity = createCategoryEntity(1L, "Test Category");
        ProductEntity productEntity1 = createProductEntity(1L, "Product 1", 10.0, categoryEntity);
        ProductEntity productEntity2 = createProductEntity(2L, "Product 2", 20.0, categoryEntity);

        when(jpaProductRepository.findAll()).thenReturn(Arrays.asList(productEntity1, productEntity2));

        List<Product> products = productRepository.findAll();

        assertEquals(2, products.size());
        assertEquals(1L, products.get(0).getId());
        assertEquals(2L, products.get(1).getId());
        assertEquals(10.0, products.get(0).getPrice());
        assertEquals(20.0, products.get(1).getPrice());
        assertEquals(1L, products.get(0).getCategory().getId());
        assertEquals(1L, products.get(1).getCategory().getId());
        verify(jpaProductRepository, times(1)).findAll();
    }

    @Test
    void testDeleteById() {
        Long idToDelete = 1L;

        productRepository.deleteById(idToDelete);

        verify(jpaProductRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    void testExistsById() {
        Long idToCheck = 1L;

        when(jpaProductRepository.existsById(idToCheck)).thenReturn(true);

        boolean exists = productRepository.existsById(idToCheck);

        assertTrue(exists);
        verify(jpaProductRepository, times(1)).existsById(idToCheck);
    }

    @Test
    void testFindProductByCategoryId() {
        CategoryEntity categoryEntity = createCategoryEntity(1L, "Category 1");
        ProductEntity productEntity1 = createProductEntity(1L, "Product 1", 10.0, categoryEntity);
        ProductEntity productEntity2 = createProductEntity(2L, "Product 2", 20.0, categoryEntity);

        when(jpaProductRepository.findProductByCategoryId(1L)).thenReturn(Arrays.asList(productEntity1, productEntity2));

        List<Product> products = productRepository.findProductByCategoryId(1L);

        assertEquals(2, products.size());
        assertEquals(1L, products.get(0).getId());
        assertEquals("Product 1", products.get(0).getName());
        assertEquals(10.0, products.get(0).getPrice());
        assertEquals(2L, products.get(1).getId());
        assertEquals("Product 2", products.get(1).getName());
        assertEquals(20.0, products.get(1).getPrice());
        verify(jpaProductRepository, times(1)).findProductByCategoryId(1L);
    }
}