package com.logistica.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.logistica.businessDelegate.BusinessDelegate;
import com.logistica.dtos.VentaDTO;


@Path("/services")
public class Services {

	// REST con JSON (POST)
	@POST
	@Path("/envioVenta")
	@Consumes({"application/json"})	// consumimos (recibimos) una venta como JSON. Luego se parsea automaticamente a un objeto de tipo VentaDTO
	@Produces({"text/plain"})		// enviamos un response con la respuesta ('recepcion ok' o bien 'recepcion error: 'mensaje de error'')
	public String recibirVenta(VentaDTO venta) {
		try {
			BusinessDelegate.getInstance().registrarVenta(venta);

			return "recepcion ok";

		} catch (Exception e) {
			e.printStackTrace();

			return "recepcion error: " + e.getMessage();
		}
	}

}
