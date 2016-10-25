package com.logistica.entities;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.logistica.dtos.DespachoDTO;

@Entity
@DiscriminatorValue("Des")
public class Despacho extends Modulo{
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="idModulo")
	private Direccion direccion;
	private String descripcion;
	private boolean estado;
	
	
	public Despacho(int id, Direccion direccion, String descripcion,
			boolean estado) {
		this.id = id;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.estado = estado;
	}


	public Despacho() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Direccion getDireccion() {
		return direccion;
	}


	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public DespachoDTO toDTO(){
		DespachoDTO dto = new DespachoDTO();
		dto.setDescripcion(this.descripcion);
		dto.setDireccion(direccion.toDTO());
		dto.setId(this.id);
		dto.setEstado(this.estado);
		dto.setIp(this.ip);
		return dto;
	}
	
}