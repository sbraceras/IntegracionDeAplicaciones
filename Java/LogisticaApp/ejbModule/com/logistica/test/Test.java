package com.logistica.test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistica.dtos.ArticuloDTO;
import com.logistica.dtos.ClienteDTO;
import com.logistica.dtos.CoordenadaDTO;
import com.logistica.dtos.DespachoDTO;
import com.logistica.dtos.DireccionDTO;
import com.logistica.dtos.ItemVentaDTO;
import com.logistica.dtos.OrdenDespachoDTO;
import com.logistica.dtos.VentaDTO;
import com.logistica.entityBeans.ItemVenta;
import com.logistica.interfaces.FacadeEJBRemote;
import com.logistica.interfaces.StatelessAdmDespachosBeanRemote;
import com.logistica.jsons.GoogleRespuestaJSON;
import com.logistica.sessionBeans.AdmDespachosBean;


public class Test {

	
	@EJB
	AdmDespachosBean despachos;
	public static void main(String[] args) throws Exception {

		
		final Hashtable jndiProperties2 = new Hashtable();
		jndiProperties2.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties2.put("jboss.naming.client.ejb.context", true);

		final Context context2 = new InitialContext(jndiProperties2);
		final String earName2 = "LogisticaAppEAR";
		final String ejbModuleName2 = "LogisticaApp";
		final String distinctName2 = "";
		final String ejbClassName2 = "AdmDespachosBean";
		final String fullInterfaceName2 = StatelessAdmDespachosBeanRemote.class.getName();

		String lookupName2 = "ejb:" + earName2 + "/" + ejbModuleName2 + "/" + distinctName2 + "/" + ejbClassName2 + "!"
				+ fullInterfaceName2;

		System.out.println("Conectando a " + lookupName2);
		
		StatelessAdmDespachosBeanRemote mbr2 = (StatelessAdmDespachosBeanRemote) context2.lookup(lookupName2);

		
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context", true);

		final Context context = new InitialContext(jndiProperties);
		final String earName = "LogisticaAppEAR";
		final String ejbModuleName = "LogisticaApp";
		final String distinctName = "";
		final String ejbClassName = "FacadeEJB";
		final String fullInterfaceName = FacadeEJBRemote.class.getName();

		String lookupName = "ejb:" + earName + "/" + ejbModuleName + "/" + distinctName + "/" + ejbClassName + "!"
				+ fullInterfaceName;

		System.out.println("Conectando a " + lookupName);
		
		FacadeEJBRemote mbr = (FacadeEJBRemote) context.lookup(lookupName);

//		mbr2.enviarOrdenesEmitidas();
		
		//Borrar!!
		
//		mbr.agregarPortalWeb();
		
				

		VentaDTO venta = new VentaDTO();
		ClienteDTO cliente = new ClienteDTO();
		DireccionDTO direccion = new DireccionDTO();
		CoordenadaDTO coordenada = new CoordenadaDTO();
		ArrayList<ItemVentaDTO> itemsVenta = new ArrayList<ItemVentaDTO>();
		ArticuloDTO articuloDTO = new ArticuloDTO();
		ItemVentaDTO itemDTO = new ItemVentaDTO();
		itemDTO.setCantidad(5);
		articuloDTO.setDescripcion("Latita 600 gr");
		articuloDTO.setMarca("Coca Cola");
		articuloDTO.setIdProducto(123456);
		articuloDTO.setNombreDeposito("G01");
		articuloDTO.setPrecio(20.05F);
		itemDTO.setArticulo(articuloDTO);
		itemsVenta.add(itemDTO);
		
		coordenada.setLatitud(-34.6166938);
		coordenada.setLongitud(-58.3815892);
		direccion.setAltura(4444);
		direccion.setCalle("Larralde");
		direccion.setDpto("A");
		direccion.setPiso(2);
		direccion.setCoordenada(coordenada);
		cliente.setApellido("Miani");
		cliente.setNombre("Agustin");
		cliente.setDni(37702257);
		cliente.setId(1);
		cliente.setMail("agustinmiani@gmail.com");
		cliente.setDireccion(direccion);
		 
		venta.setCliente(cliente);
		venta.setFechaHoraVenta(Calendar.getInstance().getTime());
		venta.setId(2);
		venta.setIpModulo("192.168.1.0");
		venta.setItemsVenta(itemsVenta);
		venta.setNombrePortal("G10");
		venta.setMonto(2150);
		venta.setItemsVenta(itemsVenta);


		// enviamos la venta al Session Bean!
		mbr.recepcionDeVenta(venta);
//		mbr2.obetenerModulo();
//		mbr2.cargarDespachos();
		//Levanto las ventas sin Orden Asociada
//		List<VentaDTO> ventasSinDespacho = mbr2.listarVentasSinOrdenDespacho();
		
		
		OrdenDespachoDTO ordenDTO = new OrdenDespachoDTO();
		DespachoDTO despachoDTO = new DespachoDTO();
		despachoDTO.setNombre("DESPACHO_GRUPO_5");
		ordenDTO.setDespacho(despachoDTO);
		
		venta.setOrdenDespacho(ordenDTO);
		
//		mbr2.emitirOrdenDespacho(venta);

		
			
		DespachoDTO despacho = mbr2.obtenerDespachoCercanoCliente(venta);
		String auxiliar = despacho.getIp();
		
		
		String apiKey= "AIzaSyBrrSMBrm1CLgq1bgBjOM_Zyp6xwUHLVko";
		String latitudOrigen= "-34.610359";
		String longitudOrigen= "-58.374992";
		String latitudDestino= "-34.610583";
		String longitudDestino="-58.379535";
		String medioTransporte= "driving";
//		String medioTransporte= "walking";

		URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?"
				+ "origins=" + latitudOrigen + ",%20" + longitudOrigen + "&"
				+ "destinations=" + latitudDestino + ",%20" + longitudDestino + "&"
				+ "language=es" + "&"
				+ "mode=" + medioTransporte + "&"
				+ "key=" + apiKey);

		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

		if(urlConnection.getResponseCode() != 200) {
			throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
		}

		ObjectMapper mapper = new ObjectMapper();
		String response = IOUtils.toString(urlConnection.getInputStream());
		GoogleRespuestaJSON maps = mapper.readValue(response, GoogleRespuestaJSON.class);
		System.out.println("La distancia es: "+ maps.getRows()[0].getElements()[0].getDistance().getValue());	
		
	}

}
