package com.linkTIC.inventory.adapters.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.linkTIC.inventory.adapters.dto.InventoryDTO;
import com.linkTIC.inventory.domain.model.Inventory;
import com.linkTIC.inventory.domain.model.payload.InventoryResponse;
import com.linkTIC.inventory.outbound.persistence.jpa.InventoryEntity;

public class InventoryMapper {

    public static InventoryResponse mapToUserResponse(Inventory inventory) {
    	InventoryResponse InventoryResponse = new InventoryResponse();
    	InventoryResponse.setId(inventory.getId());
    	InventoryResponse.setProducto_id(inventory.getProducto_id());
    	InventoryResponse.setCantidad(inventory.getCantidad());
        return InventoryResponse;
    }
    
    public static InventoryDTO toDto(Inventory i) {
    	if (i == null) return null;
    	InventoryDTO dto = new InventoryDTO();
    	dto.setId(i.getId());
    	dto.setProducto_id(i.getProducto_id());
    	dto.setCantidad(i.getCantidad());
    	return dto;
    }


    public static Inventory toDomain(InventoryDTO dto) {
    	if (dto == null) return null;
    	Inventory i = new Inventory();
    	i.setId(dto.getId());
    	i.setProducto_id(dto.getProducto_id());
    	i.setCantidad(dto.getCantidad());
    	return i;
    }
    
    public static Inventory toDomainFromOptional(Optional<InventoryEntity> optInventory) {
    	if (optInventory == null) return null;
    	Inventory i = new Inventory();
    	i.setId(optInventory.get().getId());
    	i.setProducto_id(optInventory.get().getProducto_id());
    	i.setCantidad(optInventory.get().getCantidad());
    	return i;
    }
    
    public static Inventory toDomainFromOptionalAux(Optional<Inventory> optInventory) {
    	if (optInventory == null) return null;
    	Inventory i = new Inventory();
    	i.setId(optInventory.get().getId());
    	i.setProducto_id(optInventory.get().getProducto_id());
    	i.setCantidad(optInventory.get().getCantidad());
    	return i;
    }


    public static List<InventoryDTO> toDtoList(List<Inventory> list) {
    	return list.stream().map(InventoryMapper::toDto).collect(Collectors.toList());
    }

}