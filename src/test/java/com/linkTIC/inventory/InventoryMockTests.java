package com.linkTIC.inventory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.linkTIC.inventory.adapters.mapper.InventoryMapper;
import com.linkTIC.inventory.adapters.outbound.persistence.InventoryRepositoryAdapter;
import com.linkTIC.inventory.adapters.outbound.persistence.repository.InventoryRepository;
import com.linkTIC.inventory.application.service.InventoryService;
import com.linkTIC.inventory.domain.model.Inventory;
import com.linkTIC.inventory.outbound.persistence.jpa.InventoryEntity;

@ExtendWith(MockitoExtension.class)
class InventoryMockTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;
    
    @Autowired
    private InventoryRepository springRepo;

    @Autowired
    private InventoryRepositoryAdapter adapter;

    @Test
    void shouldSaveInventory() {
    	Long inventoryId = 1L;
   	 	Long productId = 2L;
   	 	Inventory inventory = new Inventory(inventoryId, productId, 100);
   	 
		 InventoryEntity savedEntity = new InventoryEntity();
		 savedEntity.setProducto_id(productId);
		 savedEntity.setCantidad(100);
		
		 when(springRepo.save(any(InventoryEntity.class))).thenReturn(savedEntity);
		
		 Inventory out = adapter.save(inventory);
		
		 assertThat(out.getId()).isEqualTo(inventoryId);
		 //verify(springRepo).save(any(ProductEntity.class));
    }
    
    
    @Test
    void shouldUpdateInventory() {
    	Long inventoryId = 1L;
   	 	Long productId = 2L;
   	 	Inventory inventory = new Inventory(inventoryId, productId, 100);
   	 	
   	 	Inventory createInv = adapter.save(inventory);
   	 
   	 	Optional<Inventory> findInv = adapter.findById(createInv.getId());
   	 	
   	 	Inventory inventoryUpdate = InventoryMapper.toDomainFromOptionalAux(findInv);
   	
   	 	inventoryUpdate.setCantidad(150);
   	 	
   	 	InventoryEntity inventoryEntityUpdate = new InventoryEntity();
   	 	inventoryEntityUpdate.setId(inventoryId);
   	 	inventoryEntityUpdate.setProducto_id(productId);
   	 	inventoryEntityUpdate.setCantidad(150);
		
		 when(springRepo.save(any(InventoryEntity.class))).thenReturn(inventoryEntityUpdate);
		
		 Inventory out = adapter.save(inventory);
		 
		 assertThat(out.getId()).isEqualTo(inventoryId);
		 //verify(springRepo).save(any(ProductEntity.class));
    }
}
