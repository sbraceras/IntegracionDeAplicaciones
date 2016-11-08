package com.logistica.entityBeans;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.logistica.dtos.ArticuloDTO;

@Entity
@Table(name="Articulos")
public class Articulo {
	
	@EmbeddedId
	private IdArticulo idArticulo;
	private float precio;
	private String descripcion;
	private double ventasAcumuladas;
	
	public Articulo() {
	}

	public Articulo(IdArticulo id, float precio, String descripcion, double ventasAcumuladas) {
		this.idArticulo = id;
		this.precio = precio;
		this.descripcion = descripcion;
		this.ventasAcumuladas = ventasAcumuladas;
	}
	
	public IdArticulo getId() {
		return idArticulo;
	}
	public void setId(IdArticulo id) {
		this.idArticulo = id;
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
		dto.setIdProducto(this.idArticulo.getId());
		dto.setNombreDeposito(this.idArticulo.getNombreDeposito());
		dto.setPrecio(this.precio);

		return dto;
	}
	
	public static Articulo fromDTO (ArticuloDTO dto) {
		Articulo articulo = new Articulo();

		articulo.setDescripcion(dto.getDescripcion());
		IdArticulo id = new IdArticulo();
		id.setId(dto.getIdProducto());
		id.setNombreDeposito(dto.getNombreDeposito());
		
		articulo.setId(id);
		articulo.setPrecio(dto.getPrecio());

		return articulo;
	}

}
