package com.logistica.sessionBeans;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistica.dtos.DespachoDTO;
import com.logistica.dtos.OrdenDespachoDTO;
import com.logistica.dtos.VentaDTO;
import com.logistica.entityBeans.Cliente;
import com.logistica.entityBeans.Coordenada;
import com.logistica.entityBeans.Despacho;
import com.logistica.entityBeans.Direccion;
import com.logistica.entityBeans.IDVenta;
import com.logistica.entityBeans.ItemVenta;
import com.logistica.entityBeans.Modulo;
import com.logistica.entityBeans.OrdenDespacho;
import com.logistica.entityBeans.Venta;
import com.logistica.enums.EstadoOrdenDespacho;
import com.logistica.enums.EstadoVenta;
import com.logistica.enums.TipoModulo;
import com.logistica.jsons.DespachoEnviarJSON;
import com.logistica.jsons.DespachoRespuestaJSON;
import com.logistica.jsons.GoogleRespuestaJSON;
import com.logistica.jsons.ItemDespachoEnviarJSON;

/**
 * Session Bean implementation class AdministradorDespachos
 */
@Stateless
@LocalBean
public class AdministradorDespachos {

	@PersistenceContext(unitName="MyPersistenceUnit")
	private EntityManager em;

	public List<VentaDTO> listarVentasSinOrdenDespacho (){
		
		@SuppressWarnings("unchecked")
		List<Venta> obtenidas = em.createQuery("Select venta from Venta venta where venta.estado =:estado").setParameter("estado", EstadoVenta.EnProceso).getResultList();

		List<Venta> ventas = new ArrayList<Venta>();
		
		for(Venta v: obtenidas){
			if(v.getOrdenDespacho()==null){
				ventas.add(v);
			}
		}
		
		if(ventas != null){
			//Encontro ventas
			List<VentaDTO> devolver = new ArrayList<VentaDTO>();
			//Las convierte a DTO para devolver
			for(Venta aux: ventas){
				devolver.add(aux.toDTO());
			}
			
			return devolver;
		}
		//No encontro ventas
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public DespachoDTO obtenerDespachoCercanoCliente(VentaDTO venta) throws Exception {

		Cliente cliente = em.find(Cliente.class, venta.getCliente().getDni());

		if(cliente != null){
			//El cliente Existe en nuestra base
			List<Despacho> despachos = em.createQuery("Select despacho from Despacho despacho where despacho.estado = true").getResultList();
			Despacho cercano = null;
			int distanciaMenor = 0;
			for(Despacho despacho: despachos){
				//Esta apiKey se utiliza para hacer las peticiones, ya que al ser libre tiene un limite de usos
				String apiKey= "AIzaSyBrrSMBrm1CLgq1bgBjOM_Zyp6xwUHLVko";
				//Seteo las coordenadas de origen (Por donde parte el despacho)
				String latitudOrigen= Double.toString(despacho.getDireccion().getCoordenada().getLatitud());
				String longitudOrigen= Double.toString(despacho.getDireccion().getCoordenada().getLongitud());
				//Seteo las coordenadas de Destino (El domicilio del cliente)
				String latitudDestino= Double.toString(cliente.getDireccion().getCoordenada().getLatitud());
				String longitudDestino=Double.toString(cliente.getDireccion().getCoordenada().getLongitud());
				//Se identifica el medio por el que se viaja, varia depende cual se use
				String medioTransporte= "driving";

				//Se arma toda la URL para hacer la peticion al servicio de la API de Google
				URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?"
						+ "origins=" + latitudOrigen + ",%20" + longitudOrigen + "&"
						+ "destinations=" + latitudDestino + ",%20" + longitudDestino + "&"
						+ "language=es" + "&"
						+ "mode=" + medioTransporte + "&"
						+ "key=" + apiKey);

				//Hago la peticion a Google Maps
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

		        URLConnection connection = url.openConnection();
		        connection.setDoOutput(true);

		        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
		        out.write("string=");
		        out.close();

		        BufferedReader in = new BufferedReader(
		                                    new InputStreamReader(connection.getInputStream()));

		        String decodedString;
		        StringBuffer response = new StringBuffer();
		        while ((decodedString = in.readLine()) != null) {
		            response.append(decodedString);
		        }
		        in.close();
			
		        ObjectMapper mapper = new ObjectMapper();
		        GoogleRespuestaJSON maps = mapper.readValue(response.toString(), GoogleRespuestaJSON.class);
			        
				//Controlo si es el primer Despacho que recorri o si es otro que esta mas cercano
				if((cercano == null) || (maps.getRows()[0].getElements()[0].getDistance().getValue() < distanciaMenor)){
					distanciaMenor = maps.getRows()[0].getElements()[0].getDistance().getValue();
					cercano = despacho;
				}
			
			}
			//Ya encontre el despacho mas cercano
			return cercano.toDTO();
		}
		//No encontro al cliente en la BD
		return null;
	}

	// BORRAR
	public void cargarDespachos() {		
		Direccion direccion = new Direccion();
		Despacho despacho = new Despacho();
		Coordenada coordenada = new Coordenada();
		coordenada.setLatitud(-32.953529);
		coordenada.setLongitud(-60.6715342);
		despacho.setDescripcion("Macro");
		despacho.setEstado(true);
		despacho.setIp("192.168.1.40");
		despacho.setNombre("Macro Supermercados");
		despacho.setTipoModulo(TipoModulo.Despacho);
		direccion.setCoordenada(coordenada);
		direccion.setAltura(1111);
		direccion.setCalle("Av. Ricardo Balbin");
		direccion.setDpto("Ninguno");
		direccion.setPiso(0);
		
		despacho.setDireccion(direccion);
		
		em.persist(despacho);
		
		despacho = new Despacho();
		coordenada = new Coordenada();
		direccion = new Direccion();
		coordenada.setLatitud(-34.6179003);
		coordenada.setLongitud(-58.3874423);
		despacho.setDescripcion("Diarco");
		despacho.setEstado(true);
		despacho.setIp("192.168.1.39");
		despacho.setNombre("Mayorista Diarco");
		despacho.setTipoModulo(TipoModulo.Despacho);
		direccion.setCoordenada(coordenada);
		
		direccion.setAltura(3040);
		direccion.setCalle("Av. Gral Paz");
		direccion.setDpto("Ninguno");
		direccion.setPiso(0);
		
		direccion.setCoordenada(coordenada);
		
		despacho.setDireccion(direccion);
		
		em.persist(despacho);
		
		despacho = new Despacho();
		direccion = new Direccion();
		coordenada = new Coordenada();
		despacho.setDescripcion("WallMart");
		despacho.setEstado(true);
		
		despacho.setIp("192.168.1.37");
		despacho.setNombre("WallMart Supermercados");
		despacho.setTipoModulo(TipoModulo.Despacho);
		
		direccion.setAltura(5637);
		direccion.setCalle("Av. Constituyentes");
		direccion.setDpto("Ninguno");
		direccion.setPiso(0);
		coordenada.setLatitud(-31.4027010);
		coordenada.setLongitud(-64.1644477);
		direccion.setCoordenada(coordenada);
		despacho.setDireccion(direccion);
		
		em.persist(despacho);
	}

	//En esta funcionalidad me llega la ventaDTO solo con el ID de la venta y una ordenDespachoDTO dentro de ella con el Despacho Elegido
	public void emitirOrdenDespacho(VentaDTO dto){
		
		//Obtengo de la BD la venta Persistente
		IDVenta idVenta = new IDVenta();
		idVenta.setId(dto.getId());
		idVenta.setModulo(obtenerModuloPorNombre(dto.getNombrePortal()));

		Venta venta = em.find(Venta.class, idVenta);

		//Creo la orden de Despacho en Estado Emitida
		OrdenDespacho orden = new OrdenDespacho();
		Despacho despacho = (Despacho) obtenerModuloPorNombre(dto.getOrdenDespacho().getDespacho().getNombre());
		orden.setDespacho(despacho);
		orden.setEstado(EstadoOrdenDespacho.Emitida);
		orden.setFecha(Calendar.getInstance().getTime());
		orden.setVenta(venta);
		
		//Se guarda la Nueva Orden de Despacho en la Base sin ID Externa
		//Esta ID Externa es el id que va a tener el Despacho al que le enviemos
		//De despacho al que le mandemos y recibimos la pk compuesta que ellos
		//Digan, analizar si esto va a ser una pk compuesta de nuestro lado.
		
		em.persist(orden);
		em.flush();
		venta.setOrdenDespacho(orden);
		em.merge(venta);
		
	}
	
	
	public Modulo obtenerModuloPorNombre(String nombre) {
		Modulo modulo = (Modulo) em.createQuery("Select m from Modulo m where m.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();

		if (modulo != null) {
			return modulo;
		}		
		else
		{
			//Deberia mandar una exception
			return null;
		}
	}

	@SuppressWarnings({ "unchecked" })
	public List<OrdenDespachoDTO> enviarOrdenesEmitidas() throws Exception {
		
		List<OrdenDespacho> ordenesEmitidas = em.createQuery("Select orden from OrdenDespacho orden where orden.estado =:estado").setParameter("estado", EstadoOrdenDespacho.Emitida).getResultList();
		
		if (ordenesEmitidas != null) {

			//Genero el contenido necesario para enviar JSON
			URL url;
			HttpURLConnection urlConnection;
			ObjectMapper mapper;
			String jsonInString;
			DespachoRespuestaJSON respuestaDespacho;

			//Genero el JSON
			DespachoEnviarJSON json;
			ItemDespachoEnviarJSON itemJson;
			List<ItemDespachoEnviarJSON> itemsJson = new ArrayList<ItemDespachoEnviarJSON>();
			List<OrdenDespachoDTO> devolver = new ArrayList<OrdenDespachoDTO>();
			for(OrdenDespacho orden: ordenesEmitidas) {
				//Ahora le tengo que pegar a cada Rest de los modulos

				//Cargo el JSON
				json = new DespachoEnviarJSON();
				json.setIdPortal(orden.getVenta().getId().getModulo().getNombre());
				json.setIdVenta(orden.getVenta().getId().getId());

				for(ItemVenta itemVenta : orden.getVenta().getItemsVenta()) {
					itemJson = new ItemDespachoEnviarJSON();
					itemJson.setCodArticulo(itemVenta.getArticulo().getIdArticulo().getId());
					itemJson.setCantidad(itemVenta.getCantidad());

					itemsJson.add(itemJson);
				}

				json.setDetalles(itemsJson);

				// Enviamos el JSON al Despacho correspondiente
				
				// ESTO ES PARA PROBAR EN LA CASA DE SOFFIAN
//				url = new URL("http://192.168.0.11:8080/LogisticaREST/rest/services/recepcionOrdenDespacho");
//				url = new URL("http://localhost:8080/LogisticaREST/rest/services/recepcionOrdenDespacho");

				// DESCOMENTAR EL SABADO Y EL DIA DE LA PRUEBA FINAL
				url = new URL("http://" + orden.getDespacho().getIp() + ":8080/" + orden.getDespacho().getUrlEnvioOrdenDespacho());

				urlConnection = (HttpURLConnection) url.openConnection();

				urlConnection.setDoOutput(true);
				urlConnection.setRequestMethod("POST");
				urlConnection.setRequestProperty("Content-Type", "application/json");

				mapper = new ObjectMapper();

				// Convierto el objeto 'DespachoEnviarJSON' (json) a formato JSON con JACKSON
				jsonInString = mapper.writeValueAsString(json);

				urlConnection.getOutputStream().write(jsonInString.getBytes());; // Envío de un string en formato Json

				InputStream respuesta = urlConnection.getInputStream();

		        respuestaDespacho = mapper.readValue(respuesta.toString(), DespachoRespuestaJSON.class);

		        if (respuestaDespacho.getProcesado().equalsIgnoreCase("true")) {
		        	// El despacho la recibio correctamente
		        	orden.setEstado(EstadoOrdenDespacho.Enviada);
		        	// Me guardo el ID propio de la Base de Datos de Despacho (IdExterna)!
		        	orden.setIdExterna(respuestaDespacho.getIdOrdenDespacho());

		        	em.merge(orden);
		        	
		        } else {
		        	// No se pudo procesar el despacho por algun motivo
		        	orden.setEstado(EstadoOrdenDespacho.Rechazada);

//		        	orden.setIdExterna(respuestaDespacho.getIdOrdenDespacho());

		        	em.merge(orden);
		        }
		        
		        devolver.add(orden.toDTO());
		        

			}

			return devolver;
		}
		
		throw new Exception("No hay Ordenes para Enviar");

	}

	public void cambiarEstadoOrdenDeDespacho(OrdenDespachoDTO ordenDespacho) throws Exception {
		OrdenDespacho orden = (OrdenDespacho) em.createQuery("select o from OrdenDespacho o where o.idExterna =:idExterna").setParameter("idExterna", ordenDespacho.getIdExterna()).getSingleResult();

		if (orden != null) {
			orden.setEstado(ordenDespacho.getEstado());
			em.merge(orden);
			//Ahora se actualiza el estado de la venta
			Venta venta = orden.getVenta();
			venta.setEstado(EstadoVenta.Finalizada);
			em.merge(venta);
		} else {
			throw new Exception("No se encontró una orden de despacho con ese identificador.");
		}
	}
	
	public List<DespachoDTO> obtenerDespachosActivos () throws Exception{
		
		@SuppressWarnings("unchecked")
		List<Despacho> despachos = em.createQuery("Select despacho from Despacho despacho where despacho.estado =:estado").setParameter("estado", true).getResultList();
		
		if(despachos != null){

			List<DespachoDTO> dtos = new ArrayList<DespachoDTO>();
			for(Despacho despacho: despachos){
				
				dtos.add(despacho.toDTO());
			}
			
			return dtos;
		}
		else
		{
			throw new Exception("No hay despachos activos");
		}
		
	}

}
