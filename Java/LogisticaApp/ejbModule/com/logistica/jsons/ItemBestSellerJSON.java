package com.logistica.jsons;

public class ItemBestSellerJSON {

	//	{"ranking":[{"codigo":"1857363",nombreDeposito:"GXX","posicion":"3"},
	private int codigo;
	private String nombreDeposito;
	private int posicion;
	
	public ItemBestSellerJSON() {
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
	
	
}
