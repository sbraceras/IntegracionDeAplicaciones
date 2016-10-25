package com.logistica.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.logistica.dtos.DireccionDTO;

@Entity
@Table(name="Direcciones")
public class Direccion {
	
	@Id
	@Column(name="idDireccion")
	private int id;

	private String calle;
	private int altura;
	private int piso;
	private String dpto;

	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn(name = "idCoordenada")
	private Coordenada coordenada;
	
	public Direccion() {
		
	}
	
	public Direccion(int id, String calle, int altura, int piso, String dpto, Coordenada coordenada) {
		this.id = id;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.dpto = dpto;
		this.coordenada = coordenada;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public int getPiso() {
		return piso;
	}
	public void setPiso(int piso) {
		this.piso = piso;
	}
	public String getDpto() {
		return dpto;
	}
	public void setDpto(String dpto) {
		this.dpto = dpto;
	}
	public Coordenada getCoordenada() {
		return coordenada;
	}
	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}
	
	public DireccionDTO toDTO(){
		DireccionDTO direccionDTO = new DireccionDTO();
		direccionDTO.setAltura(this.altura);
		direccionDTO.setCalle(this.calle);
		direccionDTO.setCoordenada(this.coordenada.toDTO());
		direccionDTO.setDpto(this.dpto);
		direccionDTO.setPiso(this.piso);
		return direccionDTO;
	}
	
	
}
