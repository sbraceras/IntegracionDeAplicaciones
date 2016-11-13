package com.logistica.dtos;

import java.io.Serializable;

public class EstandarDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
