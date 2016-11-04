package com.logistica.entityBeans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.logistica.dtos.CoordenadaDTO;

@Entity
@Table(name = "Coordenadas")
public class Coordenada {

	/*
	 * @GeneratedValue(strategy=GenerationType.AUTO) //Determinar si esto va o
	 * no con el profesor y los grupos
	 * 
	 * no podria ser 'GeneratedValue' ya que este numero, al ser OneToOne, seria
	 * el mismo que el campo 'idDireccion' de la clase 'Direccion'
	 */

	@Id
	@Column(name = "idCoordenada")
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public CoordenadaDTO toDTO() {
		CoordenadaDTO dto = new CoordenadaDTO();
		dto.setId(this.id);
		dto.setLatitud(this.latitud);
		dto.setLongitud(this.longitud);
		return dto;
	}

}
