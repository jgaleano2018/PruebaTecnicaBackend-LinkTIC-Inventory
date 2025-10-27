package com.linkTIC.inventory.domain.port.in;

import java.util.List;
import com.linkTIC.inventory.domain.model.Inventory;

public interface InventoryUseCases {
	Inventory create(Inventory inventory);
	Inventory update(Long id, Inventory inventory);
	Inventory getById(Long id);
    List<Inventory> getAll();
    void delete(Long id);
    Inventory findByProductoId(Long productoId);
}