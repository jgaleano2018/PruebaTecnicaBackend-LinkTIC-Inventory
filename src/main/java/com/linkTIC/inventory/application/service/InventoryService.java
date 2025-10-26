package com.linkTIC.inventory.application.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkTIC.inventory.adapters.mapper.InventoryMapper;
import com.linkTIC.inventory.domain.model.Inventory;
import com.linkTIC.inventory.domain.port.in.InventoryUseCases;
import com.linkTIC.inventory.domain.port.out.InventoryRepositoryPort;

@Service
public class InventoryService implements InventoryUseCases {
	
    private final InventoryRepositoryPort repository;
    private final Logger log = LoggerFactory.getLogger(InventoryService.class);
    
    public InventoryService(InventoryRepositoryPort repository){
        this.repository = repository;
    }

    @Override
    @Transactional
    public Inventory create(Inventory inventory){
    	
    	Inventory saved;
    	
    	if (inventory == null) {
    		throw new IllegalArgumentException("Inventory to create cannot be null");
        }
    	
    	try {
    		    		
    		saved = this.repository.save(inventory);
    		
    		log.info("Created inventory id={} product_id={}", saved.getId(), saved.getProducto_id());
    		
    		return saved;
    		
		
	    } catch (Exception e) {
	    	
	    	log.warn("Attempt to create inventory id={}", inventory.getId());
	        throw new IllegalArgumentException("Error: "+e.getMessage());
	    }
    }

    @Override
    @Transactional
    public Inventory update(Long id, Inventory inventory){
    	
    	Inventory existing;
    	Inventory saved;
    	
    	if (inventory == null) {
    		throw new IllegalArgumentException("Inventory to update cannot be null");
        }
    	
    	try {
	    	existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
	    	
	    	existing.setCantidad(inventory.getCantidad());
	    	
	    	saved = this.repository.save(existing);
	    	
	    	log.info("Updated inventory id={}", id);
	    	
	    	return saved;
    	  
	    } catch (Exception e) {
	    	
	    	log.warn("Attempt to update inventory id={}", inventory.getId());
	        throw new IllegalArgumentException("Error: "+e.getMessage());
	    }
    	
    }

    @Override
    public Inventory getById(Long id){
        
    	Inventory inventory = new Inventory();
    	
    	try {
    	
    		Optional<Inventory> inventoryOptional = repository.findById(id);
    		
    		inventory = InventoryMapper.toDomainFromOptionalAux(inventoryOptional);
    	
    	}
    	catch(Exception e) {
    		
    		log.warn("Attempt to get inventory id={}", inventory.getId());
    	}
    	
    	return inventory;    		
    }

    @Override
    public List<Inventory> getAll(){   	 
        return this.repository.findAll();
    }

    @Override
    @Transactional
    public void delete(Long id){
    	
    	try {
    		
    		repository.delete(id);
    		
    		log.info("Deleted inventory id={}", id);
    		
    	} catch (Exception e) {
	    	
	    	log.warn("Attempt to delete inventory id={}", id);
	        throw new IllegalArgumentException("Error: "+e.getMessage());
	    }
    }
    
    
    @Override
    public Inventory findByProductoId(Long productoId){
    	
    	Inventory inventory = new Inventory();
    	
    	try {
    	
    		inventory = InventoryMapper.toDomainFromOptional(repository.findByProductoId(productoId));
    		
    	}
    	catch(Exception e) {
    		
    		log.warn("Attempt to get inventory id={}", inventory.getId());
    		
    	}
    	
    	return inventory;    	
    }
}