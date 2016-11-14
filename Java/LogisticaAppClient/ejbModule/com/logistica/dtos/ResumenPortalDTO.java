package com.logistica.dtos;

import java.io.Serializable;
import java.util.List;

public class ResumenPortalDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	EstandarDTO portal;
	float montoTotal;
	List<VentaDTO> ventas;
	
	
	public ResumenPortalDTO(){
		
	}


	public EstandarDTO getPortal() {
		return portal;
	}


	public void setPortal(EstandarDTO portal) {
		this.portal = portal;
	}


	public float getMontoTotal() {
		return montoTotal;
	}


	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}


	public List<VentaDTO> getVentas() {
		return ventas;
	}


	public void setVentas(List<VentaDTO> ventas) {
		this.ventas = ventas;
	}
	
	
}
