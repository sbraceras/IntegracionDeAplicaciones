package com.logistica.entityBeans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.logistica.dtos.ArticuloDTO;

@Entity
@Table(name="Articulos")
public class Articulo {
	
	@Id
	@Column(name="idArticulo")
	private int id;
	private float precio;
	private String descripcion;
	private double ventasAcumuladas;
	
	public Articulo() {
	}

	public Articulo(int id, float precio, String descripcion, double ventasAcumuladas) {
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
	public double getVentasAcumuladas() {
		return ventasAcumuladas;
	}
	public void setVentasAcumuladas(double ventasAcumuladas) {
		this.ventasAcumuladas = ventasAcumuladas;
	}
	
	public ArticuloDTO toDTO() {
		ArticuloDTO dto = new ArticuloDTO();

		dto.setDescripcion(this.descripcion);
		dto.setIdProducto(this.id);
		dto.setPrecio(this.precio);

		return dto;
	}
	
	public static Articulo fromDTO (ArticuloDTO dto) {
		Articulo articulo = new Articulo();

		articulo.setDescripcion(dto.getDescripcion());
		articulo.setId(dto.getIdProducto());
		articulo.setPrecio(dto.getPrecio());

		return articulo;
	}

}
