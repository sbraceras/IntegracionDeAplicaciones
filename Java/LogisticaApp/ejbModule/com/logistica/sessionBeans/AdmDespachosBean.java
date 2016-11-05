package com.logistica.sessionBeans;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.logistica.dtos.DespachoDTO;
import com.logistica.dtos.EstandarDTO;
import com.logistica.dtos.VentaDTO;
import com.logistica.entityBeans.Cliente;
import com.logistica.entityBeans.Coordenada;
import com.logistica.entityBeans.Despacho;
import com.logistica.entityBeans.Direccion;
import com.logistica.entityBeans.Estandar;
import com.logistica.entityBeans.Venta;
import com.logistica.enums.EstadoVenta;
import com.logistica.enums.TipoModulo;
import com.logistica.interfaces.StatelessAdmDespachosBeanLocal;
import com.logistica.interfaces.StatelessAdmDespachosBeanRemote;
import com.logistica.test.CalculoDistancia;
import com.logistica.test.Response;

/**
 * Session Bean implementation class AdmDespachosBean
 */
@Stateless
@LocalBean
public class AdmDespachosBean implements StatelessAdmDespachosBeanRemote, StatelessAdmDespachosBeanLocal {

 
	@PersistenceContext(unitName="MyPersistenceUnit")
	private EntityManager em;
	
	public List<VentaDTO> listarVentasSinOrdenDespacho (){
		
		@SuppressWarnings("unchecked")
		List<Venta> ventas = em.createQuery("Select venta from Venta venta where venta.estado =:estado").setParameter("estado", EstadoVenta.EnProceso).getResultList();
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
	public DespachoDTO obtenerDespachoCercanoCliente(VentaDTO venta) throws Exception{
		
		Cliente cliente = em.find(Cliente.class, venta.getCliente().getDni());
		if(cliente != null){
			//El cliente Existe en nuestra base
			List<Despacho> despachos = em.createQuery("Select despacho from Despacho despacho").getResultList();
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

			        OutputStreamWriter out = new OutputStreamWriter(
			                                         connection.getOutputStream());
			        out.write("string=");
			        out.close();

			        BufferedReader in = new BufferedReader(
			                                    new InputStreamReader(
			                                    connection.getInputStream()));
			        String decodedString;
			        StringBuffer response = new StringBuffer();
			        while ((decodedString = in.readLine()) != null) {
			            response.append(decodedString);
			        }
			        in.close();
				
				Response maps = CalculoDistancia.obtenerDistancia(response.toString());
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
	
	
	//Borrar
	public EstandarDTO obetenerModulo(){
		
		Estandar est = em.find(Estandar.class, "Mercadolibre");
		return null;
		
	}
	
	//Borrar
	
	public void cargarDespachos(){
		
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
	
	
    
}
