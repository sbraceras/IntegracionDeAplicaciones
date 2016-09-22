package com.logistica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.logistica.dto.ArticuloDTO;

@Entity
@Table(name="Articulos")
public class Articulo {
	
	@Id
	@Column(name="idArticulo")
	private int id;
	private float precio;
	private String descripcion;
	private int ventasAcumuladas;
	
	public Articulo() {
	}

	public Articulo(int id, float precio, String descripcion, int ventasAcumuladas) {
		this.id = id;
		this.precio = precio;
		this.descripcion = descripcion;
		this.ventasAcumuladas = ventasAcumuladas;
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
	
	public ArticuloDTO toDTO() {
		ArticuloDTO dto = new ArticuloDTO();
		dto.setDescripcion(this.descripcion);
		dto.setId(this.id);
		dto.setPrecio(this.precio);
		dto.setVentasAcumuladas(ventasAcumuladas);
		return dto;
	}
	
	
}
