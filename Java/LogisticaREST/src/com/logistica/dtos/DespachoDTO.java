package com.logistica.dtos;

import java.io.Serializable;

public class DespachoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private DireccionDTO direccion;
	private String descripcion;
	private boolean estado;
	private String ip;
	
	public DespachoDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DireccionDTO getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionDTO direccion) {
		this.direccion = direccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}