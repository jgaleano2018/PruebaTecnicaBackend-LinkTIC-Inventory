package com.linkTIC.inventory.domain.port.out;

import java.util.Optional;

import com.linkTIC.inventory.domain.model.Inventory;
import com.linkTIC.inventory.outbound.persistence.jpa.InventoryEntity;

import java.util.List;

public interface InventoryRepositoryPort {
    Inventory save(Inventory inventory);
    Optional<Inventory> findById(Long id);
    List<Inventory> findAll();
    void delete(Long id);
    Optional<InventoryEntity> findByProducto_id(Long producto_id);
}