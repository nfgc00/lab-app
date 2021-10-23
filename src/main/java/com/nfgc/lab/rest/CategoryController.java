package com.nfgc.lab.rest;

import java.net.URI;
import java.util.List;

import com.nfgc.lab.entitiy.Category;
import com.nfgc.lab.entitiy.Item;
import com.nfgc.lab.service.CategoryService;
import com.nfgc.lab.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * Category Controller class
 * <br />
 * REST API Controller to perform Category operations
 * 
 * @author Fernando
 *
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemService itemService;

    @Operation(summary = "Get all Categories availables with its Attributes")
    @ApiResponse(responseCode = "200", description = "List of Categories returned OK", 
        content = { @Content(mediaType = "application/json", 
        array = @ArraySchema(schema = @Schema(implementation = Category.class))) })
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @Operation(summary = "Get a Category by ID")
    @ApiResponse(responseCode = "200", description = "Category returned OK", 
        content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)) })
    @ApiResponse(responseCode = "404", description = "Category Not Found", content = @Content)
    @GetMapping("/{id}")
    public ResponseEntity<Category> geCategory(@PathVariable Integer id) {
        System.out.println("ID: " + id);
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @Operation(summary = "Add a new Category")
    @ApiResponse(responseCode = "201", description = "Category created", 
        content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class)) })
    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        category = categoryService.save(category);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(location).body(category);
    }

    @Operation(summary = "Get all Items on a Category")
    @ApiResponse(responseCode = "200", description = "List of Items returned OK", 
        content = { @Content(mediaType = "application/json", 
        array = @ArraySchema(schema = @Schema(implementation = Item.class))) })
    @GetMapping("/{id}/items")
    public List<Item> getCategoryItems(@PathVariable Integer id) {
        return itemService.getAllByCategory(id);
    }
}
