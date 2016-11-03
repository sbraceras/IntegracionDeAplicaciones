package com.logistica.businessDelegate;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.logistica.dtos.VentaDTO;
import com.logistica.interfaces.FacadeEJBRemote;

public class BusinessDelegate {

	private static BusinessDelegate instancia = null;
	private FacadeEJBRemote stub = null;

	private BusinessDelegate() {
		try {
			stub = getStub();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public static BusinessDelegate getInstance() {
		if (instancia == null) {
			instancia = new BusinessDelegate();
		}
		return instancia;
	}

	public void registrarVenta(VentaDTO venta) throws Exception {
		try {
			stub.recepcionDeVenta(venta);
		} catch (Exception e) {
			throw new Exception("Se ha producido un error al registrar la venta. " + e.getMessage());
		}
	}

	private static FacadeEJBRemote getStub() throws Exception {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context", true);

		final Context context = new InitialContext(jndiProperties);

		final String earName = "LogisticaAppEAR"; // Nombre del m�dulo EAR
		final String ejbModuleName = "LogisticaApp"; // Nombre del m�dulo EJB
		final String distinctName = ""; // Opcional para resolver repeticiones en nombres
		final String ejbClassName = "FacadeEJB"; // Nombre corto del EJB. Es quien implementa la interface 'FacadeEJBRemote'
		final String fullInterfaceName = FacadeEJBRemote.class.getName();

		String lookupName = "ejb:" + earName + "/" + ejbModuleName + "/" + distinctName + "/" + 
				ejbClassName + "!" + fullInterfaceName;

		System.out.println("Conectando a " + lookupName);

		return (FacadeEJBRemote) context.lookup(lookupName);
	}

}