package com.nfgc.lab.entitiy;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Attribute Entity class, stores the data related to the Attributes of a Category
 * 
 * @author Fernando
 *
 */
@Entity
public class Attribute {
    
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Category category;

    public Attribute() {
    }
    public Attribute(Integer id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}
