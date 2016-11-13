package com.logistica.sessionBeans.facades;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.logistica.dtos.BestSellerDTO;
import com.logistica.dtos.DespachoDTO;
import com.logistica.dtos.LogDTO;
import com.logistica.dtos.OrdenDespachoDTO;
import com.logistica.dtos.RecepcionBestSellerDTO;
import com.logistica.dtos.VentaDTO;
import com.logistica.interfaces.FacadeEJBLocal;
import com.logistica.interfaces.FacadeEJBRemote;
import com.logistica.sessionBeans.AdministradorAuditoria;
import com.logistica.sessionBeans.AdministradorDespachos;
import com.logistica.sessionBeans.AdministradorReportes;
import com.logistica.sessionBeans.AdministradorVentas;

/**
 * Session Bean implementation class FacadeEJB
 */
@Stateless
@LocalBean
public class FacadeEJB implements FacadeEJBLocal, FacadeEJBRemote {

	@EJB
	AdministradorVentas av;

	@EJB
	AdministradorAuditoria aa;

	@EJB
	AdministradorDespachos ad;
	
	@EJB
	AdministradorReportes ar;

	/**
	 * Default constructor.
	 */
	public FacadeEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void recepcionDeVenta(VentaDTO ventaDTO) throws Exception {
		av.recepcionDeVenta(ventaDTO);
	}

	@Override
	public void recepcionDeLog(LogDTO logDTO) throws Exception {
		aa.guardarLog(logDTO);
	}

	@Override
	public List<LogDTO> buscarLogs() throws Exception {
		return aa.buscarLogs();
	}

	@Override
	public List<VentaDTO> listarVentasSinOrdenDespacho() {
		return ad.listarVentasSinOrdenDespacho();
	}

	@Override
	public DespachoDTO obtenerDespachoCercanoCliente(VentaDTO venta) throws Exception {
		return ad.obtenerDespachoCercanoCliente(venta);
	}

	@Override
	public void emitirOrdenDespacho(VentaDTO dto) {
		ad.emitirOrdenDespacho(dto);
	}

	@Override
	public void enviarOrdenesEmitidas() throws Exception {
		ad.enviarOrdenesEmitidas();
	}

	@Override
	public void cambiarEstadoOrdenDeDespacho(OrdenDespachoDTO ordenDespacho) throws Exception {
		ad.cambiarEstadoOrdenDeDespacho(ordenDespacho);
	}
	
	@Override
	public List<RecepcionBestSellerDTO> enviarReporteBestSeller() throws Exception{
		return ar.enviarReporteBestSeller();
	}
	
	@Override
	public BestSellerDTO generarReporteBestSeller(){
		return ar.generarReporteBestSeller();
	}
	
	@Override
	public List<DespachoDTO> obtenerDespachosActivos () throws Exception{
		return ad.obtenerDespachosActivos();
	}
}
