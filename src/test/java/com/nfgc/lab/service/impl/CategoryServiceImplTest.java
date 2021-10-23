package com.nfgc.lab.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.nfgc.lab.entitiy.Category;
import com.nfgc.lab.repository.AttributeRepository;
import com.nfgc.lab.repository.CategoryRepository;
import com.nfgc.lab.service.CategoryService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Category Service implementation test class
 * 
 * @author Fernando
 *
 */
@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {
    
    @TestConfiguration
    static class CategoryServiceImplTestContextConfiguration {

        @Bean
        public CategoryService categoryService() {
            return new CategoryServiceImpl();
        }
    }

    @Autowired
    CategoryServiceImpl categoryService;

    @MockBean
    CategoryRepository categoryRepository;

    @MockBean
    AttributeRepository attributeRepository;

    /**
     * Test method for {@link CategoryService#save(Category)}
     */
    @Test
    public void whenSave_thenReturnCategory() {
        Category cat1 = new Category(1, "Cat1");
        
        Category saved = categoryService.save(cat1);

        assertNotNull(saved);
        verify(categoryRepository, times(1)).save(any());
    }

    /**
     * Test method for {@link CategoryService#getById(Integer)}
     */
    @Test
    public void whenGetById_thenReturnCategoryObject() {
        Category cat1 = new Category(1, "Cat1");

        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(cat1));

        Category category = categoryService.getById(1);

        assertNotNull(category);
        verify(categoryRepository, times(1)).findById(anyInt());
    }

    /**
     * Test method for {@link CategoryService#getAll()}
     */
    @Test
    public void whenGetAll_thenReturnCategoriesList() {
        Category cat1 = new Category(1, "Cat1");
        Category cat2 = new Category(2, "Cat2");

        when(categoryRepository.findAll()).thenReturn(Arrays.asList(cat1, cat2));

        List<Category> categories = categoryService.getAll();

        assertEquals(2, categories.size());
        verify(categoryRepository, times(1)).findAll();
    }
}
