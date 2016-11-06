package com.logistica.jsons;

public class DespachoRespuestaJSON {
	
	
	private String procesado; //"true"
	private int idOrdenDespacho; // 1234
	
	public DespachoRespuestaJSON() {
	}

	public String getProcesado() {
		return procesado;
	}

	public void setProcesado(String procesado) {
		this.procesado = procesado;
	}

	public int getIdOrdenDespacho() {
		return idOrdenDespacho;
	}

	public void setIdOrdenDespacho(int idOrdenDespacho) {
		this.idOrdenDespacho = idOrdenDespacho;
	}

	
	
}
