package com.logistica.dtos;

import java.io.Serializable;
import java.util.Date;

import com.logistica.enums.TipoLog;

public class LogDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private Date fecha;
	private TipoLog tipo;
	private String descripcion;
	private String ip;
	private String nombreModulo;

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

	public TipoLog getTipo() {
		return tipo;
	}

	public void setTipo(TipoLog tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNombreModulo() {
		return nombreModulo;
	}

	public void setNombreModulo(String nombreModulo) {
		this.nombreModulo = nombreModulo;
	}

}
