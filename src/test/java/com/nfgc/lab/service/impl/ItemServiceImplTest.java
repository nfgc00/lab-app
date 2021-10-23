package com.nfgc.lab.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.nfgc.lab.entitiy.Attribute;
import com.nfgc.lab.entitiy.AttributeValue;
import com.nfgc.lab.entitiy.Category;
import com.nfgc.lab.entitiy.Item;
import com.nfgc.lab.repository.AttributeValueRepository;
import com.nfgc.lab.repository.ItemRepository;
import com.nfgc.lab.service.AttributeService;
import com.nfgc.lab.service.CategoryService;
import com.nfgc.lab.service.ItemService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Item Service implementation test class
 * 
 * @author Fernando
 *
 */
@RunWith(SpringRunner.class)
public class ItemServiceImplTest {
    
    @TestConfiguration
    static class ItemServiceImplTestContextConfiguration {

        @Bean
        public ItemService itemService() {
            return new ItemServiceImpl();
        }
    }

    @Autowired
    ItemService itemService;

    @MockBean
    ItemRepository itemRepository;

    @MockBean
    AttributeValueRepository attributeValueRepository;

    @MockBean
    CategoryService categoryService;

    @MockBean
    AttributeService attributeService;

    /**
     * Test method for {@link ItemService#save(Item)}
     */
    @Test
    public void whenSave_thenReturnItem() {
        Item item = new Item();

        when(itemRepository.save(any())).thenReturn(item);

        Item saved = itemService.save(item);

        assertNotNull(saved);
        verify(itemRepository, times(1)).save(any());
    }

    /**
     * Test method for {@link ItemService#update(Integer, Item)}
     */
    @Test
    public void whenUpdate_thenReturnItem() {
        Item item = new Item();
        item.setId(1);
        item.setCategory(new Category(5, "Category5"));
        Attribute att = new Attribute();
        att.setId(10);
        AttributeValue av1 = new AttributeValue();
        av1.setAttribute(att);
        av1.setAttributeId(10);
        item.setAttributesValues(Arrays.asList(av1));

        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));

        Item updated = itemService.update(1, item);

        assertNotNull(updated);
        verify(attributeValueRepository, times(1)).save(any());
    }

    /**
     * Test method for {@link ItemService#getAll()}
     */
    @Test
    public void whenGetAll_thenReturnItemsList() {
        Item item1 = new Item();
        Item item2 = new Item();
        Category cat = new Category(1, "Category1");
        item1.setCategory(cat);
        item2.setCategory(cat);
        item1.setAttributesValues(new ArrayList<>());
        item2.setAttributesValues(new ArrayList<>());

        when(itemRepository.findAll()).thenReturn(Arrays.asList(item1, item2));

        List<Item> items = itemService.getAll();

        assertEquals(2, items.size());
        verify(itemRepository, times(1)).findAll();
    }

    /**
     * Test method for {@link ItemService#getById(Integer)}
     */
    @Test
    public void whenGetById_thenReturnItem() {
        Item item = new Item();
        Category cat = new Category(1, "Category1");
        item.setCategory(cat);
        item.setAttributesValues(new ArrayList<>());

        when(itemRepository.findById(anyInt())).thenReturn(Optional.of(item));

        Item returned = itemService.getById(1);

        assertNotNull(returned);
        verify(itemRepository, times(1)).findById(anyInt());
    }

    /**
     * Test method for {@link ItemService#getAllByCategory(Integer)}
     */
    @Test
    public void whenGetAllByCategory_thenReturnItemsList() {
        Item item1 = new Item();
        Item item2 = new Item();
        Category cat = new Category(1, "Category1");
        item1.setCategory(cat);
        item2.setCategory(cat);
        item1.setAttributesValues(new ArrayList<>());
        item2.setAttributesValues(new ArrayList<>());

        when(categoryService.getById(anyInt())).thenReturn(cat);
        when(itemRepository.findByCategory(any())).thenReturn(Arrays.asList(item1, item2));

        List<Item> items = itemService.getAllByCategory(1);

        assertEquals(2, items.size());
        verify(itemRepository, times(1)).findByCategory(any());
    }
}
