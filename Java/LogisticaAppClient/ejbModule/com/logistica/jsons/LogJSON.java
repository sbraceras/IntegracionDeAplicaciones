package com.logistica.jsons;

import java.util.Date;

public class LogJSON {

	private Date fecha; // "2002-05-30T09:00:00"
	private String tipo; // "Portal"
	private String modulo; // "GXX"
	private String descripcion; // "Descripcion del evento"

	public LogJSON() {
		
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
