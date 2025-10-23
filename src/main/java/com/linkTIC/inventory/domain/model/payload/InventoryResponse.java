package com.linkTIC.inventory.domain.model.payload;

public class InventoryResponse {
	private Long id; // matches id_inventory INT
	private Long producto_id;
	private int cantidad;
	
	public Long getId() {
		return id;
	}
	public void setId(Long long1) {
		this.id = long1;
	}
	public Long getProducto_id() {
		return producto_id;
	}
	public void setProducto_id(Long producto_id) {
		this.producto_id = producto_id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}		
	
}
