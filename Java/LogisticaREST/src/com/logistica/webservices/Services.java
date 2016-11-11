package com.logistica.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.logistica.businessDelegate.BusinessDelegate;
import com.logistica.dtos.CoordenadaDTO;
import com.logistica.dtos.DireccionDTO;
import com.logistica.dtos.LogDTO;
import com.logistica.dtos.OrdenDespachoDTO;
import com.logistica.dtos.VentaDTO;
import com.logistica.enums.EstadoOrdenDespacho;
import com.logistica.jsons.DespachoEnviarJSON;
import com.logistica.jsons.LogJSON;
import com.logistica.jsons.RecepcionCambioEstadoOrdenJSON;
import com.logistica.jsons.RespuestaCambioEstadoOrdenJSON;
import com.logistica.jsons.RespuestaRecepcionVentaJSON;


@Path("/services")
public class Services {

	// REST con JSON (POST)
	@POST
	@Path("/envioVenta")
	@Consumes({"application/json"})	// consumimos (recibimos) una venta como JSON. Luego se parsea automaticamente a un objeto de tipo VentaDTO
	@Produces({"application/json"})		// enviamos un response con la respuesta ('recepcion ok' o bien 'recepcion error: 'mensaje de error'')
	public RespuestaRecepcionVentaJSON recibirVenta(VentaDTO venta) {
		
		RespuestaRecepcionVentaJSON respuesta = new RespuestaRecepcionVentaJSON();
		try {
			CoordenadaDTO coordenada = new CoordenadaDTO();
			coordenada.setLatitud(venta.getLatitud());
			coordenada.setLongitud(venta.getLongitud());

			DireccionDTO direccion = new DireccionDTO();
			direccion.setCoordenada(coordenada);

			venta.getCliente().setDireccion(direccion);

			BusinessDelegate.getInstance().registrarVenta(venta);
			
			respuesta.setResultado(true);
			respuesta.setMessage("Recepcion de venta exitosa");
			
			return respuesta;

		} catch (Exception e) {
			e.printStackTrace();
			respuesta.setResultado(false);
			respuesta.setMessage("Error en la recepcion");
			return respuesta;
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

			return logsDTO;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@POST
	@Path("/cambioEstadoOrdenDeDespacho")
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public RespuestaCambioEstadoOrdenJSON cambiarEstadoOrdenDeDespacho(RecepcionCambioEstadoOrdenJSON json) {
		RespuestaCambioEstadoOrdenJSON respuesta = new RespuestaCambioEstadoOrdenJSON();

		try {
			OrdenDespachoDTO ordenDespacho = new OrdenDespachoDTO();
			ordenDespacho.setIdExterna(json.getIdOrdenDespacho());
			ordenDespacho.setEstado(EstadoOrdenDespacho.Entregada); // SUPONEMOS, POR AHORA, QUE SIEMPRE LLEGARA ESTE ESTADO!!!

			BusinessDelegate.getInstance().cambiarEstadoOrdenDeDespacho(ordenDespacho);

			respuesta.setResultado(true);
			respuesta.setMessage("Actualizacion de estado exitosa");
		} catch(Exception e) {
			respuesta.setResultado(false);
			respuesta.setMessage(e.getMessage());
		}

		return respuesta;
	}










	//////////////////////////////////////////////////////////////////
	// PRUEBA RECEPCION ORDEN DESPACHO. Simulamos ser modulo Despacho. 
	@POST
	@Path("/recepcionOrdenDespacho")
	@Consumes({"application/json"})	// Indica que consume (recibe) un objeto Json en el POST. Lo parsea a un objeto de tipo LogDTO
	@Produces({"application/json"})
	public String recepcionOrdenDespacho(DespachoEnviarJSON despachoEnviar) {

		try {
//			throw new Exception();
			int i = 0;
			System.out.println("hola");

			return "{\"procesado\" : \"true\" , \"idOrdenDespacho\" : 1234 }";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"procesado\" : \"false\" }";
		}

	}

}
