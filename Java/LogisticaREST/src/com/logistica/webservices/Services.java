package com.logistica.webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.logistica.dtos.VentaDTO;

@Path("/services")
public class Services {

	// REST con JSON (POST)
	@POST
	@Path("/envioVenta")
	@Consumes({"application/json"})	// consumimos (recibimos) una venta como JSON. Luego se parsea automaticamente a un objeto de tipo VentaDTO
	@Produces({"text/plain"})		// enviamos un response con la respuesta ("recibido")
	public String recibirVenta(VentaDTO venta) {
		return "recibido";
	}

}
