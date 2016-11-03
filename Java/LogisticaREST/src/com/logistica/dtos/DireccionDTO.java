package com.logistica.dtos;

import java.io.Serializable;

public class DireccionDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String calle;
	private int altura;
	private int piso;
	private String dpto;
	private CoordenadaDTO coordenada;
	
	
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
	public CoordenadaDTO getCoordenada() {
		return coordenada;
	}
	public void setCoordenada(CoordenadaDTO coordenada) {
		this.coordenada = coordenada;
	}
	
	
	
}
