package com.logistica.ejb;

import javax.ejb.Remote;

import com.logistica.dtos.VentaDTO;

@Remote
public interface StatelessAdministradorVentasRemote {
	
	public boolean recepcionDeVenta (VentaDTO ventaDTO);
	
	public void agregarPortalWeb ();

}
