package com.logistica.dtos;

import java.io.Serializable;

public class ItemVentaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private double cantidad;
	private ArticuloDTO articulo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public ArticuloDTO getArticulo() {
		return articulo;
	}
	public void setArticulo(ArticuloDTO articulo) {
		this.articulo = articulo;
	}
	
	
}
