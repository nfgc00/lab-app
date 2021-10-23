package com.nfgc.lab.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nfgc.lab.entitiy.AttributeValue;
import com.nfgc.lab.entitiy.Item;
import com.nfgc.lab.service.ItemService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Item Controller test class
 * 
 * @author Fernando
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class)
public class ItemControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ItemService itemService;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * Test method for {@link ItemController#getAllItems()}
     * 
     * @throws Exception
     */
    @Test
    public void whenGetAllItems_thenReturnItemsArray() throws Exception {
        Item item1 = new Item();
        Item item2 = new Item();

        when(itemService.getAll()).thenReturn(Arrays.asList(item1, item2));

        mockMvc.perform(get("/items").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)));
        verify(itemService, VerificationModeFactory.times(1)).getAll();
    }

    /**
     * Test method for {@link ItemController#getItem(Integer)}
     * 
     * @throws Exception
     */
    @Test
    public void whenGetItem_thenReturnItemsObject() throws Exception {
        Item item1 = new Item();
        item1.setId(1);

        when(itemService.getById(anyInt())).thenReturn(item1);

        mockMvc.perform(get("/items/{id}", 1).contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(item1.getId()));
        verify(itemService, VerificationModeFactory.times(1)).getById(anyInt());
    }

    /**
     * Test method for {@link ItemController#addItem(Item)}
     * 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    public void whenPostItem_thenCreateItem() throws JsonProcessingException, Exception {
        Item item1 = new Item();
        item1.setId(1);

        when(itemService.save(any())).thenReturn(item1);

        mockMvc.perform(post("/items").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(item1)))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(item1.getId()));
        verify(itemService, VerificationModeFactory.times(1)).save(any());
            
    }

    /**
     * Test method for {@link ItemController#updateItem(Integer, Item)}
     * 
     * @throws JsonProcessingException
     * @throws Exception
     */
    @Test
    public void whenPutItem_thenUpdateItem() throws JsonProcessingException, Exception {
        Item item1 = new Item();
        AttributeValue av = new AttributeValue();
        av.setValue("value1");
        item1.setAttributesValues(Arrays.asList(av));

        when(itemService.update(anyInt(), any())).thenReturn(item1);

        mockMvc.perform(put("/items/{id}", 1).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(item1)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.attributesValues[0].value").value(av.getValue()));
        verify(itemService, VerificationModeFactory.times(1)).update(anyInt(), any());
    }
}
