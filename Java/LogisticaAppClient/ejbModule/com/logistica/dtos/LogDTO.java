package com.logistica.dtos;

import java.io.Serializable;
import java.util.Date;

import com.logistica.enums.TipoModulo;

public class LogDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private Date fecha;
	private TipoModulo tipoModulo;
	private String nombreModulo;
	private String descripcion;

	public LogDTO() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public TipoModulo getTipoModulo() {
		return tipoModulo;
	}

	public void setTipoModulo(TipoModulo tipoModulo) {
		this.tipoModulo = tipoModulo;
	}

	public String getNombreModulo() {
		return nombreModulo;
	}

	public void setNombreModulo(String nombreModulo) {
		this.nombreModulo = nombreModulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
