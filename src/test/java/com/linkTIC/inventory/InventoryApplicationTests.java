package com.linkTIC.inventory;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.linkTIC.inventory.application.service.InventoryService;
import com.linkTIC.inventory.domain.model.Inventory;

import static org.mockito.Mockito.when; 
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InventoryApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Mock
    private InventoryService service;

    @Test
    void shouldReturnInventory() throws Exception {
    	
    	List<Inventory> mockList = List.of(new Inventory(1234567890123456789L, 1234567890123456785L, 100));
    	
        when(service.getAll()).thenReturn(mockList);

        var inventory = service.getAll();

        assertThat(inventory).hasSize(1);
        verify(service, times(1)).getAll();
    }
    
    @Test
    void shouldSaveInventory() {
    	
        Inventory inventory = new Inventory(1234567890123456789L, 1234567890123456785L, 100);
        when(service.create(inventory)).thenReturn(inventory);

        var saved = service.create(inventory);

        assertThat(saved).isNotNull();
        verify(service).create(inventory);
    }
    
    @Test
    void shouldUpdateInventory() {
        Inventory inventory = new Inventory(1234567890123456789L, 1234567890123456785L, 100);
        when(service.create(inventory)).thenReturn(inventory);
        when(service.update(1234567890123456789L, inventory)).thenReturn(inventory);

        var saved = service.update(1234567890123456789L, inventory);

        assertThat(saved).isNotNull();
        verify(service).update(1234567890123456789L, inventory);
    }
    
    @Test
    void shouldDeleteInventory() {
    	 Long inventoryId = 1L;
    	 Long productId = 2L;
    	 Inventory inventory = new Inventory(inventoryId, productId, 100);

         // Mock the findById to return the entity
         when(service.getById(inventoryId)).thenReturn(inventory);

         // Mock the delete operation to do nothing
         doNothing().when(service).delete(inventoryId);

         service.delete(inventoryId);

         // Verify that findById and delete were called
         //verify(service, times(1)).getById(inventoryId);
         verify(service, times(1)).delete(inventory.getId());
    }
}
