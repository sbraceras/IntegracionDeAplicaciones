package com.logistica.sessionBeans;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistica.dtos.BestSellerDTO;
import com.logistica.dtos.ItemBestSellerDTO;
import com.logistica.dtos.RecepcionBestSellerDTO;
import com.logistica.dtos.VentaDTO;
import com.logistica.entityBeans.Articulo;
import com.logistica.entityBeans.Modulo;
import com.logistica.entityBeans.Venta;
import com.logistica.enums.TipoModulo;
import com.logistica.jsons.BestSellerJSON;
import com.logistica.jsons.ItemBestSellerJSON;
import com.logistica.jsons.RecepcionBestSellerJSON;

@Stateless
@LocalBean
public class AdministradorReportes {

	@PersistenceContext(unitName="MyPersistenceUnit")
	private EntityManager em;

	public List<RecepcionBestSellerDTO> enviarReporteBestSeller() throws Exception {
		//Levanto los 10 Productos mas vendidos

		
			BestSellerJSON bestSeller = generarBestSellerJSON();
			
			if(bestSeller != null){
				
				@SuppressWarnings("unchecked")
				List<Modulo> portales = em.createQuery("Select modulo from Modulo modulo where modulo.tipoModulo =:tipoModulo").setParameter("tipoModulo", TipoModulo.PortalWeb).getResultList();
				List<RecepcionBestSellerDTO> estadoRecepciones = new ArrayList<RecepcionBestSellerDTO>();
				RecepcionBestSellerJSON recepcionPortal;
				RecepcionBestSellerDTO recepcionPortalDTO;
				if(portales != null){
					
					URL url;
					HttpURLConnection urlConnection;
					ObjectMapper mapper;
					String jsonInString;
					for(Modulo modulo: portales){
						url = new URL("http://"+modulo.getIp()+":8080/PortalWebREST/rest/services/recibirBestSeller");
						urlConnection = (HttpURLConnection) url.openConnection();
						urlConnection.setDoOutput(true);
						urlConnection.setRequestMethod("POST");
						urlConnection.setRequestProperty("Content-Type", "application/json");
						mapper = new ObjectMapper();
						
						urlConnection = (HttpURLConnection) url.openConnection();
	
						urlConnection.setDoOutput(true);
						urlConnection.setRequestMethod("POST");
						urlConnection.setRequestProperty("Content-Type", "application/json");
						mapper = new ObjectMapper();
						
						//Convierto el json a string con el JACKSON
						jsonInString = mapper.writeValueAsString(bestSeller);
						
						urlConnection.getOutputStream().write(jsonInString.getBytes());; // Envio de un string en formato Json
						
						
						// Convierto el objeto 'DespachoEnviarJSON' (json) a formato JSON con JACKSON
						jsonInString = mapper.writeValueAsString(bestSeller);

						urlConnection.getOutputStream().write(jsonInString.getBytes());; // Envío de un string en formato Json

						InputStream respuesta = urlConnection.getInputStream();

						//Obtengo la respuesta del Portal
						
						recepcionPortal = new RecepcionBestSellerJSON();
				        recepcionPortal = mapper.readValue(respuesta.toString(), RecepcionBestSellerJSON.class);
				        
				        //Lo paso a DTO para mandar la respuesta al sitio web
				        
				        recepcionPortalDTO = new RecepcionBestSellerDTO();
				        recepcionPortalDTO.setEstado(recepcionPortal.getEstado());
				        recepcionPortalDTO.setMensaje(recepcionPortal.getMensaje());
				        recepcionPortalDTO.setNombrePortal(modulo.getNombre());
				        estadoRecepciones.add(recepcionPortalDTO);
				        
						}
					
					//Devuelvo el resultado de las Recepciones
					
					return estadoRecepciones;
					}
					else
					{
						//Deberia lanzar una exception
						return null;
					}
				
				}
				else
				{
				//Habria que lanzar una exception
				return null;
				}
	}
	
	public BestSellerDTO generarReporteBestSeller (){
		
		
		@SuppressWarnings("unchecked")
		List<Articulo> articulosVendidos = em.createQuery("Select articulo from Articulo articulo order by articulo.ventasAcumuladas desc").setFirstResult(0).setMaxResults(10).getResultList();
		
		BestSellerDTO bestSeller = new BestSellerDTO();
		
		List<ItemBestSellerDTO> itemsDTO = new ArrayList<ItemBestSellerDTO>();
		ItemBestSellerDTO itemBestSellerDTO;
		int posicion = 1;
		for(Articulo articulo: articulosVendidos){
			itemBestSellerDTO = new ItemBestSellerDTO();
			
//			itemBestSellerJSON.setCodigo(articulo.getId().toString()); //Esto es si llegan a querer el codigo compuesto, pero en el Excel no se veia asi
			itemBestSellerDTO.setCodigo(articulo.getIdArticulo().getId());
			itemBestSellerDTO.setNombreDeposito(articulo.getIdArticulo().getNombreDeposito());
			itemBestSellerDTO.setPosicion(posicion);
			itemBestSellerDTO.setVentasAcumuladas(articulo.getVentasAcumuladas());
			posicion++;
			itemsDTO.add(itemBestSellerDTO);
		}
		
		bestSeller.setItems(itemsDTO);
		return bestSeller;
	}
	
	public BestSellerJSON generarBestSellerJSON(){
		
		BestSellerDTO dto = generarReporteBestSeller();
		
		BestSellerJSON bestSeller = new BestSellerJSON();
		List<ItemBestSellerJSON> itemsBest = new ArrayList<ItemBestSellerJSON>();
		ItemBestSellerJSON itemJSON;	
		for(ItemBestSellerDTO itemDTO: dto.getItems()){
			itemJSON = new ItemBestSellerJSON();
			itemJSON.setCodigo(itemDTO.getCodigo());
			itemJSON.setNombreDeposito(itemDTO.getNombreDeposito());
			itemJSON.setPosicion(itemDTO.getPosicion());
			itemsBest.add(itemJSON);
		}
		bestSeller.setRanking(itemsBest);
		return bestSeller;
		
	}
	
	//Este lo utiliza la Web para hacer el Reporte de Ventas Recibidas
	public List<VentaDTO> obtenerVentasRecibidas() throws Exception{
		
		@SuppressWarnings("unchecked")
		//Obtengo las ventas de la BD
		List<Venta> ventas = em.createQuery("Select venta from Venta venta").getResultList();
		
		//Verifico si devolvio algo
		if(ventas != null){
			
			List<VentaDTO> devolver = new ArrayList<VentaDTO>();
			//Las convierto a DTO
			for(Venta venta: ventas){
				devolver.add(venta.toDTO());
			}
			
			return devolver;			
		}
		//No encontro nada, entonces tiro exception
		else
		{
			throw new Exception("No se han encontrado Ventas");
		}
	}

}

