package com.logistica.dtos;

import java.io.Serializable;

public class RecepcionBestSellerDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	String estado;
	String mensaje;
	String nombrePortal;

	public RecepcionBestSellerDTO() {
		
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getNombrePortal() {
		return nombrePortal;
	}

	public void setNombrePortal(String nombrePortal) {
		this.nombrePortal = nombrePortal;
	}

}
