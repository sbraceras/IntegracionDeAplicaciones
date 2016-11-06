package com.logistica.interfaces;

import javax.ejb.Remote;

import com.logistica.dtos.LogDTO;
import com.logistica.dtos.VentaDTO;

@Remote
public interface FacadeEJBRemote {

	public void recepcionDeVenta(VentaDTO ventaDTO) throws Exception;
	
	public void recepcionDeLog(LogDTO logDTO) throws Exception;

	public void agregarPortalWeb();

}
