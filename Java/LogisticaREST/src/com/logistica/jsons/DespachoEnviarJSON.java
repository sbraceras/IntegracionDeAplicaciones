package com.logistica.jsons;

import java.util.List;

public class DespachoEnviarJSON {

	private String idPortal;// "Gxx"
	private int idVenta;	// 1
	private List<ItemDespachoEnviarJSON> detalles;

	public DespachoEnviarJSON() {
		
	}

	public String getIdPortal() {
		return idPortal;
	}

	public void setIdPortal(String idPortal) {
		this.idPortal = idPortal;
	}

	public int getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	public List<ItemDespachoEnviarJSON> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<ItemDespachoEnviarJSON> detalles) {
		this.detalles = detalles;
	}

}
