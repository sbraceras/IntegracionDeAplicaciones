package com.logistica.jsons;

public class RecepcionCambioEstadoOrdenJSON {

	private int idOrdenDespacho;// 5
	private String estado; // "Entregado"

	public RecepcionCambioEstadoOrdenJSON() {
		
	}

	public int getIdOrdenDespacho() {
		return idOrdenDespacho;
	}

	public void setIdOrdenDespacho(int idOrdenDespacho) {
		this.idOrdenDespacho = idOrdenDespacho;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
