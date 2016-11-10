package com.logistica.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.logistica.dtos.DespachoDTO;
import com.logistica.dtos.LogDTO;
import com.logistica.dtos.OrdenDespachoDTO;
import com.logistica.dtos.VentaDTO;

@Local
public interface FacadeEJBLocal {

	// METODOS QUE IMPLEMENTA EL SESSION BEAN 'AdministradorVentas'
	public void recepcionDeVenta(VentaDTO ventaDTO) throws Exception;
	// ************************************************************


	// METODOS QUE IMPLEMENTA EL SESSION BEAN 'AdministradorAuditoria'
	public void recepcionDeLog(LogDTO logDTO) throws Exception;

	public List<LogDTO> buscarLogs() throws Exception;
	// ************************************************************


	// METODOS QUE IMPLEMENTA EL SESSION BEAN 'AdministradorDespachos'
	public List<VentaDTO> listarVentasSinOrdenDespacho();

	public DespachoDTO obtenerDespachoCercanoCliente(VentaDTO venta) throws Exception;

	public void emitirOrdenDespacho (VentaDTO dto);

	public void enviarOrdenesEmitidas () throws Exception;

	public void cambiarEstadoOrdenDeDespacho(OrdenDespachoDTO ordenDespacho) throws Exception;
	// ************************************************************


	// METODOS QUE IMPLEMENTA EL SESSION BEAN 'AdministradorReportes'

	// ************************************************************

}
