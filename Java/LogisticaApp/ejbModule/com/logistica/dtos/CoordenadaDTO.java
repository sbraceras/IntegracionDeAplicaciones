package com.logistica.dtos;
public class CoordenadaDTO {
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
