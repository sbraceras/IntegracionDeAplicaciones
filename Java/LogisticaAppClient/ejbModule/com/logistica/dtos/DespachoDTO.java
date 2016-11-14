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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + (estado ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime
				* result
				+ ((urlEnvioOrdenDespacho == null) ? 0 : urlEnvioOrdenDespacho
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this.id == ((DespachoDTO) obj).getId())
			return true;
		return false;
	}
	
	
	
}
