package com.logistica.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.logistica.businessDelegate.BusinessDelegate;
import com.logistica.dtos.LogDTO;
import com.logistica.dtos.VentaDTO;
import com.logistica.jsons.LogJSON;


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
	
	@POST
	@Path("/guardarLog")
	@Consumes({ "application/json" })	// Indica que consume (recibe) un objeto Json en el POST. Lo parsea a un objeto de tipo LogDTO
	@Produces({"text/plain"})
	public String guardarLog(LogJSON log){
		try {
			LogDTO logDTO = new LogDTO();
			logDTO.setFecha(log.getFecha());
			logDTO.setNombreModulo(log.getModulo());
			logDTO.setDescripcion(log.getDescripcion());
			BusinessDelegate.getInstance().registrarLog(logDTO);
			return  "Log guardado correctamente";
		} catch (Exception e) {
			e.printStackTrace();
			return "El log no pudo guardarse correctamente debido a: " + e.getMessage();
		}
	}
	
	@GET
	@Path("/buscarLogs")
	@Produces({ "application/json" })
	public List<LogDTO> buscarLogs(){
		List<LogDTO> logsDTO = new ArrayList<LogDTO>();
		try {
			logsDTO = BusinessDelegate.getInstance().buscarLogs();
		} catch (Exception e) {
			e.printStackTrace();
			return logsDTO;
		}
		return logsDTO;
	}

}
