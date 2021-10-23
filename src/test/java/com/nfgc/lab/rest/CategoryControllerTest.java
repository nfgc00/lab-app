package com.nfgc.lab.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nfgc.lab.entitiy.Category;
import com.nfgc.lab.entitiy.Item;
import com.nfgc.lab.service.CategoryService;
import com.nfgc.lab.service.ItemService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Category Controller test class
 * 
 * @author Fernando
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CategoryService categoryService;

    @MockBean
    ItemService itemService;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * Test method for {@link CategoryController#getAllCategories()}
     * 
     * @throws Exception
     */
    @Test
    public void whenGetAllCategories_thenReturnCategoriesArray() throws Exception {
        Category cat1 = new Category(1, "Cat1");
        Category cat2 = new Category(2, "Cat2");

        when(categoryService.getAll()).thenReturn(Arrays.asList(cat1, cat2));
        
        mockMvc.perform(get("/categories").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
        verify(categoryService, times(1)).getAll();
    }
    
    /**
     * Test method for {@link CategoryController#geCategory(Integer)}
     * 
     * @throws Exception
     */
    @Test
    public void whenGeCategory_thenReturnCategoryObject() throws Exception {
        Category cat1 = new Category(1, "Cat1");

        when(categoryService.getById(anyInt())).thenReturn(cat1);

        mockMvc.perform(get("/categories/{id}", 1).contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(cat1.getName()));
        verify(categoryService, times(1)).getById(anyInt());
    }

    /**
     * Test method for {@link CategoryController#addCategory(Category)}
     * 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    public void whenPostCategory_thenCreateCategroy() throws JsonProcessingException, Exception {
        Category cat1 = new Category(1, "Cat1");

        when(categoryService.save(any())).thenReturn(cat1);

        mockMvc.perform(post("/categories").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(cat1)))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value(cat1.getName()));
            verify(categoryService, times(1)).save(any());
    }

    /**
     * Test method for {@link CategoryController#getCategoryItems(Integer)}
     * 
     * @throws Exception
     */
    @Test
    public void whenGetCategoryItems_thenReturnItemsArray() throws Exception {
        Item item1 = new Item();
        Item item2 = new Item();

        when(itemService.getAllByCategory(anyInt())).thenReturn(Arrays.asList(item1, item2));

        mockMvc.perform(get("/categories/{id}/items", 1).contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
        verify(itemService, times(1)).getAllByCategory(anyInt());
    }
}
