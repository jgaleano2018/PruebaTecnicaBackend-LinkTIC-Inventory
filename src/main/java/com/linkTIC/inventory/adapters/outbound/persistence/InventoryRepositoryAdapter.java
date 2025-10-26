package com.linkTIC.inventory.adapters.outbound.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.linkTIC.inventory.adapters.outbound.persistence.repository.InventoryRepository;
import com.linkTIC.inventory.domain.model.Inventory;
import com.linkTIC.inventory.domain.port.out.InventoryRepositoryPort;
import com.linkTIC.inventory.outbound.persistence.jpa.InventoryEntity;


@Component
public class InventoryRepositoryAdapter implements InventoryRepositoryPort {

    private final InventoryRepository inventoryRepo;

    public InventoryRepositoryAdapter(InventoryRepository InventoryRepo){
        this.inventoryRepo = InventoryRepo;
    }

    private Inventory toDomain(InventoryEntity e) { 
    	return new Inventory(e.getId(), e.getProducto_id(), e.getCantidad());
    }
    
    private InventoryEntity toEntity(Inventory i) { 
    	InventoryEntity e = new InventoryEntity();    	
    	e.setId(i.getId());
    	e.setProducto_id(i.getProducto_id());
    	e.setCantidad(i.getCantidad());
		return e;
	}

    @Override
    public Inventory save(Inventory inventory){
    	InventoryEntity entity = toEntity(inventory);
        if (entity.getId() == null) entity.setId(inventory.getId());
        InventoryEntity saved = inventoryRepo.save(entity);
        return toDomain(saved);
    }

    @Override
	public Optional<Inventory> findById(Long id) {
    	return inventoryRepo.findById(id).map(this::toDomain);
	}
	
	
	@Override
	public List<Inventory> findAll() {
		return inventoryRepo.findAll().stream().map(this::toDomain).collect(Collectors.toList());
	}
	
    @Override public void delete(Long id){ inventoryRepo.deleteById(id); }
    
    @Override
   	public Optional<InventoryEntity> findByProductoId(Long productoId) {
   		return inventoryRepo.findByProductoId(productoId);
   	}
}
