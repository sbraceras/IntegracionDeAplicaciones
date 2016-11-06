package com.logistica.jsons;

public class ItemDespachoEnviarJSON {

	private int codArticulo;
	private double cantidad;
	
		
	public ItemDespachoEnviarJSON(int codArticulo, double cantidad) {
		this.codArticulo = codArticulo;
		this.cantidad = cantidad;
	}
	public int getCodArticulo() {
		return codArticulo;
	}
	public void setCodArticulo(int codArticulo) {
		this.codArticulo = codArticulo;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}	
}
