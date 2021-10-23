package com.nfgc.lab.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.nfgc.lab.entitiy.Attribute;
import com.nfgc.lab.exception.ResourceNotFoundException;
import com.nfgc.lab.repository.AttributeRepository;
import com.nfgc.lab.service.AttributeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  Attribute Service implementation class to perform business logic operations.
 * 
 * @author Fernando
 *
 */
@Service
@Transactional
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    @Override
    public Attribute save(Attribute attribute) {
        return attributeRepository.save(attribute);
    }

    @Override
    public Attribute getById(Integer id) {
        return attributeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Attribute", "Id", id));
    }
    
    // @Override
    // public List<Attribute> getByCategory(Integer category) {
    //     return attributeRepository.findAll();
    // }
}
