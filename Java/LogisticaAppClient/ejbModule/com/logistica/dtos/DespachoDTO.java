package com.logistica.dtos;

import java.io.Serializable;

public class DespachoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private DireccionDTO direccion;
	private String descripcion;
	private boolean estado;
	private String urlEnvioOrdenDespacho;
	private String ip;
	private String nombre;

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrlEnvioOrdenDespacho() {
		return urlEnvioOrdenDespacho;
	}

	public void setUrlEnvioOrdenDespacho(String urlEnvioOrdenDespacho) {
		this.urlEnvioOrdenDespacho = urlEnvioOrdenDespacho;
	}
	
}
