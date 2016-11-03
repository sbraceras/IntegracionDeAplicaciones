package com.logistica.ejb;

import javax.ejb.Remote;

import com.logistica.dtos.VentaDTO;

@Remote
public interface FacadeEJBRemote {
	
	public void recepcionDeVenta(VentaDTO ventaDTO) throws Exception;
	
	public void agregarPortalWeb();

}
