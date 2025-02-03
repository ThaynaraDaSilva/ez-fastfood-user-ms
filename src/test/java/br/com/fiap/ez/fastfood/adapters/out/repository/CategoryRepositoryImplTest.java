package br.com.fiap.ez.fastfood.adapters.out.repository;

import br.com.fiap.ez.fastfood.infrastructure.persistence.CategoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryRepositoryImplTest {

    private JpaCategoryRepository jpaCategoryRepository;
    private CategoryRepositoryImpl categoryRepository;

    @BeforeEach
    void setUp() {
        jpaCategoryRepository = Mockito.mock(JpaCategoryRepository.class);
        categoryRepository = new CategoryRepositoryImpl(jpaCategoryRepository);
    }

    @Test
    void findById_withExistingId_returnsCategory() {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(1L);
        entity.setName("Fast Food");

        when(jpaCategoryRepository.findCategoryById(1L)).thenReturn(entity);

        Category category = categoryRepository.findById(1L);

        assertNotNull(category);
        assertEquals(1L, category.getId());
        assertEquals("Fast Food", category.getName());
    }

    @Test
    void findById_withNonExistingId_returnsNull() {
        when(jpaCategoryRepository.findCategoryById(999L)).thenReturn(null);

        Category category = categoryRepository.findById(999L);

        assertNull(category);
    }

    @Test
    void findById_withNullId_returnsNull() {
        when(jpaCategoryRepository.findCategoryById(null)).thenReturn(null);

        Category category = categoryRepository.findById(null);

        assertNull(category);
    }
}