package com.linkTIC.inventory.application.service;

import java.util.List;

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
    	
    	try {
    		    		
    		saved = this.repository.save(inventory);
    		
    		log.info("Created inventory id={} product_id={}", saved.getId(), saved.getProducto_id());
    		
    		return saved;
    		
		
	    } catch (Exception e) {
	    	
	    	log.warn("Attempt to create inventory id={}", inventory.getId());
	        throw new EntityNotFoundException("Error: "+e.getMessage());
	    }
    }

    @Override
    @Transactional
    public Inventory update(Long id, Inventory inventory){
    	
    	Inventory existing;
    	Inventory saved;
    	
    	try {
	    	existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
	    	
	    	existing.setCantidad(inventory.getCantidad());
	    	
	    	saved = this.repository.save(existing);
	    	
	    	log.info("Updated inventory id={}", id);
	    	
	    	return saved;
    	  
	    } catch (Exception e) {
	    	
	    	log.warn("Attempt to update inventory id={}", inventory.getId());
	        throw new EntityNotFoundException("Error: "+e.getMessage());
	    }
    	
    }

    @Override
    public Inventory getById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
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
	        throw new EntityNotFoundException("Error: "+e.getMessage());
	    }
    }
    
    
    @Override
    public Inventory findByProducto_id(Long producto_id){
        return InventoryMapper.toDomainFromOptional(repository.findByProducto_id(producto_id));
    }
}