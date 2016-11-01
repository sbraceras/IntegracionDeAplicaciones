package com.logistica.test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistica.dtos.ClienteDTO;
import com.logistica.dtos.CoordenadaDTO;
import com.logistica.dtos.DireccionDTO;
import com.logistica.dtos.ItemVentaDTO;
import com.logistica.dtos.VentaDTO;
import com.logistica.ejb.StatelessAdministradorVentasRemote;

public class Test {

	
	public static void main(String[] args) throws Exception {

		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context", true);

		final Context context = new InitialContext(jndiProperties);
		final String earName = "LogisticaAppEAR";
		final String ejbModuleName = "LogisticaApp";
		final String distinctName = "";
		final String ejbClassName = "AdministradorVentas";
		final String fullInterfaceName = StatelessAdministradorVentasRemote.class.getName();

		String lookupName = "ejb:" + earName + "/" + ejbModuleName + "/" + distinctName + "/" + ejbClassName + "!"
				+ fullInterfaceName;

		System.out.println("Conectando a " + lookupName);
		
		StatelessAdministradorVentasRemote mbr = (StatelessAdministradorVentasRemote) context.lookup(lookupName);

		
		//Borrar!!
		
		mbr.agregarPortalWeb();

		VentaDTO venta = new VentaDTO();
		ClienteDTO cliente = new ClienteDTO();
		DireccionDTO direccion = new DireccionDTO();
		CoordenadaDTO coordenada = new CoordenadaDTO();
		ArrayList<ItemVentaDTO> itemsVenta = new ArrayList<ItemVentaDTO>();
		coordenada.setLatitud(-34.6166938);
		coordenada.setLongitud(-58.3815892);
		coordenada.setId(1);
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
		venta.setFechaHora(Calendar.getInstance().getTime());
		venta.setId(2);
		venta.setIpModulo("192.168.1.0");
		venta.setItemsVenta(itemsVenta);
		venta.setModulo("Mercadolibre");
		venta.setMontoTotal(2150);

		
		// enviamos la venta al Session Bean!
		mbr.recepcionDeVenta(venta);

		String apiKey= "AIzaSyBrrSMBrm1CLgq1bgBjOM_Zyp6xwUHLVko";
		String latitudOrigen= "-34.610359";
		String longitudOrigen= "-58.374992";
		String latitudDestino= "-34.610583";
		String longitudDestino="-58.379535";
		String medioTransporte= "driving";
//		String medioTransporte= "walking";
		URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins="+latitudOrigen+",%20"+longitudOrigen+"&destinations="+latitudDestino+",%20"+longitudDestino+"&language=es&mode="+medioTransporte+"&key="+apiKey);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		if(urlConnection.getResponseCode() != 200) {
		throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
		}
		ObjectMapper mapper = new ObjectMapper();
		String response = IOUtils.toString(urlConnection.getInputStream());
		Response maps = mapper.readValue(response, Response.class);
		System.out.println("La distancia es: "+ maps.getRows()[0].getElements()[0].getDistance().getValue());
	
		
	}

}
