package com.logistica.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.logistica.dtos.DespachoDTO;
import com.logistica.dtos.EstandarDTO;
import com.logistica.dtos.VentaDTO;

@Local
public interface StatelessAdmDespachosBeanLocal {
	
	public List<VentaDTO> listarVentasSinOrdenDespacho();
	public DespachoDTO obtenerDespachoCercanoCliente(VentaDTO venta) throws Exception;
	
	//Borrar
	public EstandarDTO obetenerModulo();
	//Borrar
	public void cargarDespachos();

}
