package com.logistica.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.logistica.dto.CoordenadaDTO;

@Entity
@Table(name="Coordenadas")
public class Coordenada {
	
	@Id
	@Column(name="idCoordenada")
	@GeneratedValue(strategy=GenerationType.AUTO) //Determinar si esto va o no con el profesor y los grupos
	private int id;
	private double latitud;
	private double longitud;
	
	
	public Coordenada(int id, double latitud, double longitud) {
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Coordenada() {
		
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
	
	public CoordenadaDTO toDTO(){
		CoordenadaDTO dto = new CoordenadaDTO();
		dto.setId(this.id);
		dto.setLatitud(this.latitud);
		dto.setLongitud(this.longitud);
		return dto;
	}
	
}
