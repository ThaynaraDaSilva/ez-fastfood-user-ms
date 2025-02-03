package br.com.fiap.ez.fastfood.adapters.in.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CatalogControllerTest {

    @Mock
    private ProductUseCase productUseCase;

    @InjectMocks
    private CatalogController catalogController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        ProductDTO productDTO = new ProductDTO();
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        when(productUseCase.save(any(ProductDTO.class))).thenReturn(productResponseDTO);

        ResponseEntity<ProductResponseDTO> response = catalogController.createProduct(productDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(productResponseDTO, response.getBody());
        verify(productUseCase, times(1)).save(productDTO);
    }

    @Test
    void testUpdateProduct() {
        Long productId = 1L;
        ProductDTO productDTO = new ProductDTO();
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        when(productUseCase.updateProduct(eq(productId), any(ProductDTO.class))).thenReturn(productResponseDTO);

        ResponseEntity<ProductResponseDTO> response = catalogController.updateProduct(productId, productDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productResponseDTO, response.getBody());
        verify(productUseCase, times(1)).updateProduct(productId, productDTO);
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;

        ResponseEntity<Void> response = catalogController.deleteProduct(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productUseCase, times(1)).deleteById(productId);
    }

    @Test
    void testGetAllProducts() {
        ProductResponseDTO product1 = new ProductResponseDTO();
        ProductResponseDTO product2 = new ProductResponseDTO();
        List<ProductResponseDTO> productList = Arrays.asList(product1, product2);
        when(productUseCase.findAll()).thenReturn(productList);

        ResponseEntity<List<ProductResponseDTO>> response = catalogController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
        verify(productUseCase, times(1)).findAll();
    }

    @Test
    void testGetProductById() {
        Long productId = 1L;
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        when(productUseCase.findById(productId)).thenReturn(productResponseDTO);

        ResponseEntity<ProductResponseDTO> response = catalogController.getProductById(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productResponseDTO, response.getBody());
        verify(productUseCase, times(1)).findById(productId);
    }

    @Test
    void testGetProductByCategoryId() {
        Long categoryId = 1L;
        List<ProductResponseDTO> productList = Arrays.asList(new ProductResponseDTO());
        when(productUseCase.findProductByCategoryId(categoryId)).thenReturn(productList);

        ResponseEntity<?> response = catalogController.getProductByCategoryId(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
        verify(productUseCase, times(1)).findProductByCategoryId(categoryId);
    }
}