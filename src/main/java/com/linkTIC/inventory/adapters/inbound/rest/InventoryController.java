package com.linkTIC.inventory.adapters.inbound.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import com.linkTIC.inventory.adapters.dto.InventoryDTO;
import com.linkTIC.inventory.adapters.mapper.InventoryMapper;
import com.linkTIC.inventory.domain.model.Inventory;
import com.linkTIC.inventory.domain.port.in.InventoryUseCases;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/inventory")
@Tag(name = "Inventory", description = "Manage inventory")
@CrossOrigin(origins = "http://localhost:4200")
public class InventoryController {

    private final InventoryUseCases inventoryService;

    public InventoryController(InventoryUseCases inventoryService){
        this.inventoryService = inventoryService;
    }

    @PostMapping
    @Operation(summary = "Create inventory by ID", description = "Returns a single inventory")
    public ResponseEntity<InventoryDTO> create(@Valid @RequestBody InventoryDTO inventory){
            	
    	Inventory created = inventoryService.create(InventoryMapper.toDomain(inventory));
    	InventoryDTO createdResponse = InventoryMapper.toDto(created);
    	
	    return ResponseEntity
	      .created(URI.create("/api/inventory/" + createdResponse.getId()))
	      .body(createdResponse);
        
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get inventory by ID", description = "Returns a single inventory")
    public ResponseEntity<InventoryDTO> get(@PathVariable Long id){   	
        return ResponseEntity.ok(InventoryMapper.toDto(inventoryService.getById(id)));
    }

    @GetMapping
    @Operation(summary = "Get list inventory", description = "Returns a list inventory")
    public ResponseEntity<List<InventoryDTO>> list(){
        return ResponseEntity.ok(InventoryMapper.toDtoList(inventoryService.getAll()));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update inventory by ID", description = "Returns a single inventory")
    public ResponseEntity<InventoryDTO> update(@PathVariable Long id, @RequestBody InventoryDTO inventory){
        return ResponseEntity.ok(InventoryMapper.toDto(inventoryService.update(id, InventoryMapper.toDomain(inventory))));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete inventory by ID", description = "Returns empty")
    public ResponseEntity<Void> delete(@PathVariable Long id){
    	inventoryService.delete(id);
    	return ResponseEntity.noContent().build();
    }
    
    // simple health endpoint
    @GetMapping("/health")
    public ResponseEntity<String> health() {
      return ResponseEntity.ok("OK");
    }
}