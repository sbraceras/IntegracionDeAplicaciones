package com.logistica.dtos;

import java.io.Serializable;

public class ArticuloDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/* ***** Atributos del JSON ***** */
	private int idProducto;
	private String nombreDeposito;
	private String descripcion;
	private String marca;
	private float precio;
	/* ****************************** */

	private int ventasAcumuladas;

	public ArticuloDTO() {
		
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreDeposito() {
		return nombreDeposito;
	}

	public void setNombreDeposito(String nombreDeposito) {
		this.nombreDeposito = nombreDeposito;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getVentasAcumuladas() {
		return ventasAcumuladas;
	}

	public void setVentasAcumuladas(int ventasAcumuladas) {
		this.ventasAcumuladas = ventasAcumuladas;
	}

}
