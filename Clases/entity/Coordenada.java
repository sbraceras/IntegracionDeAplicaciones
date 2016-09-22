package entity;

import dtos.CoordenadaDTO;

public class Coordenada {
	private double latitud;
	private double longitud;
	
	
	public Coordenada(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}


	public Coordenada() {
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
	
	public CoordenadaDTO toDTO(){
		
		CoordenadaDTO dto = new CoordenadaDTO();
		dto.setLatitud(this.latitud);
		dto.setLongitud(this.longitud);
		return dto;
	}
	
}
