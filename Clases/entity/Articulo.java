package entity;

import dtos.ArticuloDTO;

public class Articulo {
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
