package com.logistica.dtos;

import java.io.Serializable;

public class CoordenadaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private double latitud;
	private double longitud;
	
	public CoordenadaDTO(){
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}	
	
}
