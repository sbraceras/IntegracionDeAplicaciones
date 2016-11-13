package com.logistica.dtos;

import java.io.Serializable;

public class ItemBestSellerDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	//	{"ranking":[{"codigo":"1857363",nombreDeposito:"GXX","posicion":"3"},
	private int codigo;
	private String nombreDeposito;
	private int posicion;
	private double ventasAcumuladas;
	
	public ItemBestSellerDTO() {
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombreDeposito() {
		return nombreDeposito;
	}

	public void setNombreDeposito(String nombreDeposito) {
		this.nombreDeposito = nombreDeposito;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public double getVentasAcumuladas() {
		return ventasAcumuladas;
	}

	public void setVentasAcumuladas(double ventasAcumuladas) {
		this.ventasAcumuladas = ventasAcumuladas;
	}
	
}
