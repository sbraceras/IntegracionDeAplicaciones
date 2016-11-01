package com.logistica.ejb;

import javax.ejb.Local;
import com.logistica.dtos.VentaDTO;

@Local
public interface StatelessAdministradorVentasLocal {
	
	public boolean recepcionDeVenta (VentaDTO ventaDTO);
	
	public void agregarPortalWeb ();

}
