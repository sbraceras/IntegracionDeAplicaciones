package com.logistica.dtos;

import java.io.Serializable;

public class ArticuloDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private float precio;
	private String descripcion;
	private int ventasAcumuladas;
	
	
	public ArticuloDTO (){
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getVentasAcumuladas() {
		return ventasAcumuladas;
	}
	public void setVentasAcumuladas(int ventasAcumuladas) {
		this.ventasAcumuladas = ventasAcumuladas;
	}
	
	
	
}
