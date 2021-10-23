package com.nfgc.lab.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.nfgc.lab.entitiy.Attribute;
import com.nfgc.lab.repository.AttributeRepository;
import com.nfgc.lab.service.AttributeService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Attribute Service implementation test class
 * 
 * @author Fernando
 *
 */
@RunWith(SpringRunner.class)
public class AttributeServiceImplTest {

    @TestConfiguration
    static class AttributeServiceImplTestContextConfiguration {

        @Bean
        public AttributeService attributeService() {
            return new AttributeServiceImpl();
        }
    }

    @Autowired
    AttributeService attributeService;

    @MockBean
    AttributeRepository attributeRepository;

    /**
     * Test method for {@link AttributeService#save(Attribute)}
     */
    @Test
    public void whenSave_thenReturnAttribute() {
        Attribute att = new Attribute();

        when(attributeRepository.save(any())).thenReturn(att);

        Attribute saved = attributeService.save(att);

        assertNotNull(saved);
        verify(attributeRepository, times(1)).save(any());
    }

    /**
     * Test method for {@link AttributeService#getById(Integer)}
     */
    @Test
    public void whenGetById_thenReturnAttribute() {
        Attribute att = new Attribute();

        when(attributeRepository.findById(anyInt())).thenReturn(Optional.of(att));
        
        Attribute returned = attributeService.getById(1);

        assertNotNull(returned);
        verify(attributeRepository, times(1)).findById(anyInt());
    }
}
