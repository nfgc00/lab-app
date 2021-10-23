package com.nfgc.lab.rest;

import java.net.URI;
import java.util.List;

import com.nfgc.lab.entitiy.Item;
import com.nfgc.lab.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;
    
    @Operation(summary = "Get all Items availables with its Attribute Values")
    @ApiResponse(responseCode = "200", description = "List of Items returned OK", 
        content = { @Content(mediaType = "application/json", 
        array = @ArraySchema(schema = @Schema(implementation = Item.class))) })
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAll();
    }

    @Operation(summary = "Get an Item by ID")
    @ApiResponse(responseCode = "200", description = "Item returned OK", 
        content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Item.class)) })
    @ApiResponse(responseCode = "404", description = "Item Not Found", content = @Content)
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Integer id) {
        return ResponseEntity.ok(itemService.getById(id));
    }

    @Operation(summary = "Add a new Item")
    @ApiResponse(responseCode = "201", description = "Item created", 
        content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Item.class)) })
    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        item = itemService.save(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(location).body(item);
    } 

    @Operation(summary = "Update an Item")
    @ApiResponse(responseCode = "200", description = "Item updated", 
        content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Item.class)) })
    @ApiResponse(responseCode = "404", description = "Item Not Found", content = @Content)
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Integer id, @RequestBody Item item) {
        return ResponseEntity.ok(itemService.update(id, item));
    }
}
