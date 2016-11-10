package com.logistica.jsons;

public class ItemDespachoEnviarJSON {

	private int codArticulo; // 1234
	private double cantidad; // 21

	public ItemDespachoEnviarJSON() {

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
