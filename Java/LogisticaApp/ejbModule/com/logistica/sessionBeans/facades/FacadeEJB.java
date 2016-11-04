package com.logistica.sessionBeans.facades;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.logistica.dtos.VentaDTO;
import com.logistica.interfaces.FacadeEJBLocal;
import com.logistica.interfaces.FacadeEJBRemote;
import com.logistica.sessionBeans.AdministradorVentas;

/**
 * Session Bean implementation class FacadeEJB
 */
@Stateless
@LocalBean
public class FacadeEJB implements FacadeEJBLocal, FacadeEJBRemote {

	@EJB
	AdministradorVentas av;

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
		// TODO Auto-generated method stub
		
	}

}
