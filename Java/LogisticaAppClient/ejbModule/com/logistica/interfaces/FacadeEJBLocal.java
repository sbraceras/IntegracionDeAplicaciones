package com.logistica.interfaces;

import javax.ejb.Local;
import com.logistica.dtos.VentaDTO;

@Local
public interface FacadeEJBLocal {
	
	public void recepcionDeVenta(VentaDTO ventaDTO) throws Exception;;
	
	public void agregarPortalWeb();

}
