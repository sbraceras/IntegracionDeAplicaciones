package com.logistica.jsons;

import java.util.List;

public class DespachoEnviarJSON {

	private String nombrePortal;
	private int idVenta;
	private List<ItemDespachoEnviarJSON> detalles;
	
	
	public DespachoEnviarJSON (){
		
	}	


	public String getNombrePortal() {
		return nombrePortal;
	}


	public void setNombrePortal(String nombrePortal) {
		this.nombrePortal = nombrePortal;
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
