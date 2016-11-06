package com.logistica.sessionBeans.facades;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.logistica.dtos.LogDTO;
import com.logistica.dtos.VentaDTO;
import com.logistica.interfaces.FacadeEJBLocal;
import com.logistica.interfaces.FacadeEJBRemote;
import com.logistica.sessionBeans.AdministradorLog;
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
	AdministradorLog al;

	// @EJB
	// DespachosManager dm;

	// @EJB
	// AuditoriaManager am;

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
	public void agregarPortalWeb() {
		av.agregarPortalWeb();
	}
	
	@Override
	public void recepcionDeLog(LogDTO logDTO) throws Exception {
		al.guardarLog(logDTO);
	}
	
	@Override
	public List<LogDTO> buscarLogs() throws Exception{
		return al.buscarLogs();
	}

}
