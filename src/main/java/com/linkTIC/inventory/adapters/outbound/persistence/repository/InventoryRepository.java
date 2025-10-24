package com.linkTIC.inventory.adapters.outbound.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.linkTIC.inventory.outbound.persistence.jpa.InventoryEntity;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> { 
	
	Optional<InventoryEntity> findByProducto_id(Long producto_id);
	
}