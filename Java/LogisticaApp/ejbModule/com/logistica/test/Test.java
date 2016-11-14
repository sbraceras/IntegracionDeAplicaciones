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
import com.logistica.dtos.BestSellerDTO;
import com.logistica.dtos.ClienteDTO;
import com.logistica.dtos.CoordenadaDTO;
import com.logistica.dtos.DespachoDTO;
import com.logistica.dtos.DireccionDTO;
import com.logistica.dtos.ItemVentaDTO;
import com.logistica.dtos.OrdenDespachoDTO;
import com.logistica.dtos.VentaDTO;
import com.logistica.interfaces.FacadeEJBRemote;
import com.logistica.jsons.GoogleRespuestaJSON;
import com.logistica.sessionBeans.AdministradorDespachos;
import com.logistica.sessionBeans.AdministradorReportes;


public class Test {
	
	@EJB
	AdministradorDespachos despachos;
	
	@EJB
	AdministradorReportes admReportes;
	
	

	public static void main(String[] args) throws Exception {

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

//		mbr.enviarOrdenesEmitidas();
		
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
		
		itemDTO = new ItemVentaDTO();
		itemDTO.setCantidad(20);
		articuloDTO= new ArticuloDTO();
		articuloDTO.setDescripcion("Queso Fresco");
		articuloDTO.setMarca("Sancor");
		articuloDTO.setIdProducto(123457);
		articuloDTO.setNombreDeposito("G01");
		articuloDTO.setPrecio(50.25F);
		itemDTO.setArticulo(articuloDTO);
		itemsVenta.add(itemDTO);
		
		itemDTO = new ItemVentaDTO();
		itemDTO.setCantidad(2);
		articuloDTO= new ArticuloDTO();
		articuloDTO.setDescripcion("Salchichas");
		articuloDTO.setMarca("Vienisima");
		articuloDTO.setIdProducto(123458);
		articuloDTO.setNombreDeposito("G01");
		articuloDTO.setPrecio(24.20F);
		itemDTO.setArticulo(articuloDTO);
		itemsVenta.add(itemDTO);
		
		itemDTO = new ItemVentaDTO();
		itemDTO.setCantidad(50);
		articuloDTO= new ArticuloDTO();
		articuloDTO.setDescripcion("Agua Mineral");
		articuloDTO.setMarca("Glaciar");
		articuloDTO.setIdProducto(122213);
		articuloDTO.setNombreDeposito("G01");
		articuloDTO.setPrecio(12.00F);
		itemDTO.setArticulo(articuloDTO);
		itemsVenta.add(itemDTO);
		
		itemDTO = new ItemVentaDTO();
		itemDTO.setCantidad(70);
		articuloDTO= new ArticuloDTO();
		articuloDTO.setDescripcion("Jamon Cocido");
		articuloDTO.setMarca("Paladini");
		articuloDTO.setIdProducto(122214);
		articuloDTO.setNombreDeposito("G01");
		articuloDTO.setPrecio(24.00F);
		itemDTO.setArticulo(articuloDTO);
		itemsVenta.add(itemDTO);
		
		itemDTO = new ItemVentaDTO();
		itemDTO.setCantidad(5);
		articuloDTO= new ArticuloDTO();
		articuloDTO.setDescripcion("Jabon Blanco");
		articuloDTO.setMarca("Changuito");
		articuloDTO.setIdProducto(122220);
		articuloDTO.setNombreDeposito("G01");
		articuloDTO.setPrecio(8.10F);
		itemDTO.setArticulo(articuloDTO);
		itemsVenta.add(itemDTO);
		
		itemDTO = new ItemVentaDTO();
		itemDTO.setCantidad(3);
		articuloDTO= new ArticuloDTO();
		articuloDTO.setDescripcion("Pilas AAA");
		articuloDTO.setMarca("Energiser");
		articuloDTO.setIdProducto(123333);
		articuloDTO.setNombreDeposito("G01");
		articuloDTO.setPrecio(120.00F);
		itemDTO.setArticulo(articuloDTO);
		itemsVenta.add(itemDTO);
		
		itemDTO = new ItemVentaDTO();
		itemDTO.setCantidad(30);
		articuloDTO= new ArticuloDTO();
		articuloDTO.setDescripcion("Pepsi Light 1.5L");
		articuloDTO.setMarca("Pepsi");
		articuloDTO.setIdProducto(129999);
		articuloDTO.setNombreDeposito("G01");
		articuloDTO.setPrecio(80.00F);
		itemDTO.setArticulo(articuloDTO);
		itemsVenta.add(itemDTO);
		
		itemDTO = new ItemVentaDTO();
		itemDTO.setCantidad(70);
		articuloDTO= new ArticuloDTO();
		articuloDTO.setDescripcion("Gomitas de Pelo");
		articuloDTO.setMarca("La Ventola");
		articuloDTO.setIdProducto(1321234);
		articuloDTO.setNombreDeposito("G01");
		articuloDTO.setPrecio(5.00F);
		itemDTO.setArticulo(articuloDTO);
		itemsVenta.add(itemDTO);
		
		itemDTO = new ItemVentaDTO();
		itemDTO.setCantidad(2);
		articuloDTO= new ArticuloDTO();
		articuloDTO.setDescripcion("Resma A4");
		articuloDTO.setMarca("Glaciar");
		articuloDTO.setIdProducto(171273);
		articuloDTO.setNombreDeposito("G01");
		articuloDTO.setPrecio(50.00F);
		itemDTO.setArticulo(articuloDTO);
		itemsVenta.add(itemDTO);
		
		itemDTO = new ItemVentaDTO();
		itemDTO.setCantidad(15);
		articuloDTO= new ArticuloDTO();
		articuloDTO.setDescripcion("Yerba 1KG");
		articuloDTO.setMarca("Playadito");
		articuloDTO.setIdProducto(122210);
		articuloDTO.setNombreDeposito("G01");
		articuloDTO.setPrecio(40.00F);
		itemDTO.setArticulo(articuloDTO);
		itemsVenta.add(itemDTO);
		
		itemDTO = new ItemVentaDTO();
		itemDTO.setCantidad(4);
		articuloDTO= new ArticuloDTO();
		articuloDTO.setDescripcion("Papas 100 gr");
		articuloDTO.setMarca("Lays");
		articuloDTO.setIdProducto(123213);
		articuloDTO.setNombreDeposito("G01");
		articuloDTO.setPrecio(35.00F);
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
		venta.setId(18);
		venta.setIpModulo("192.168.1.0");
		venta.setItemsVenta(itemsVenta);
		venta.setNombrePortal("G10");
		venta.setMonto(2150);
		venta.setItemsVenta(itemsVenta);


		// enviamos la venta al Session Bean!
		mbr.recepcionDeVenta(venta);
		
//		BestSellerDTO bestSellerWeb = mbr.generarReporteBestSeller();
		
//		bestSellerWeb.getItems();

//		mbr.obetenerModulo();
//		mbr.cargarDespachos();

		// levanto las ventas sin Orden Asociada
//		List<VentaDTO> ventasSinDespacho = mbr.listarVentasSinOrdenDespacho();

//		OrdenDespachoDTO ordenDTO = new OrdenDespachoDTO();
//		DespachoDTO despachoDTO = new DespachoDTO();
//		despachoDTO.setNombre("G05");
//		ordenDTO.setDespacho(despachoDTO);

//		venta.setOrdenDespacho(ordenDTO);

//		mbr.emitirOrdenDespacho(venta);

//		mbr.enviarOrdenesEmitidas();
		
//		mbr.enviarReporteBestSeller();

//		DespachoDTO despacho = mbr.obtenerDespachoCercanoCliente(venta);
//		String auxiliar = despacho.getIp();
//
//		String apiKey= "AIzaSyBrrSMBrm1CLgq1bgBjOM_Zyp6xwUHLVko";
//		String latitudOrigen= "-34.610359";
//		String longitudOrigen= "-58.374992";
//		String latitudDestino= "-34.610583";
//		String longitudDestino="-58.379535";
//		String medioTransporte= "driving";
//		String medioTransporte= "walking";

//		URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?"
//				+ "origins=" + latitudOrigen + ",%20" + longitudOrigen + "&"
//				+ "destinations=" + latitudDestino + ",%20" + longitudDestino + "&"
//				+ "language=es" + "&"
//				+ "mode=" + medioTransporte + "&"
//				+ "key=" + apiKey);
//
//		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//
//		if(urlConnection.getResponseCode() != 200) {
//			throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
//		}
//
//		ObjectMapper mapper = new ObjectMapper();
//		String response = IOUtils.toString(urlConnection.getInputStream());
//		GoogleRespuestaJSON maps = mapper.readValue(response, GoogleRespuestaJSON.class);
//		System.out.println("La distancia es: "+ maps.getRows()[0].getElements()[0].getDistance().getValue());	
		
	}

}
