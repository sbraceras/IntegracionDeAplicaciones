package com.logistica.sessionBeans;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistica.entityBeans.Articulo;
import com.logistica.entityBeans.Modulo;
import com.logistica.enums.TipoModulo;
import com.logistica.jsons.BestSellerJSON;
import com.logistica.jsons.DespachoRespuestaJSON;
import com.logistica.jsons.ItemBestSellerJSON;
import com.logistica.jsons.RecepcionBestSellerJSON;

@Stateless
@LocalBean
public class AdministradorReportes implements AdministradorReportesRemote, AdministradorReportesLocal {

	@PersistenceContext(unitName="MyPersistenceUnit")
	private EntityManager em;
	
	
	public List<RecepcionBestSellerJSON> enviarReporteBestSeller() throws IOException{
		
		//Levanto los 10 Productos mas vendidos
		
		@SuppressWarnings("unchecked")
		List<Articulo> articulosVendidos = em.createQuery("Select articulo from Articulo articulo order by articulo.ventasAcumuladas desc").setFirstResult(0).setMaxResults(10).getResultList();
		
		if(articulosVendidos != null){
			BestSellerJSON bestSeller = new BestSellerJSON();
			List<ItemBestSellerJSON> itemsJSON = new ArrayList<ItemBestSellerJSON>();
			ItemBestSellerJSON itemBestSellerJSON;
			int posicion = 1;
			for(Articulo articulo: articulosVendidos){
				itemBestSellerJSON = new ItemBestSellerJSON();
				
//				itemBestSellerJSON.setCodigo(articulo.getId().toString()); //Esto es si llegan a querer el codigo compuesto, pero en el Excel no se veia asi
				itemBestSellerJSON.setCodigo(articulo.getId().getId());
				itemBestSellerJSON.setNombreDeposito(articulo.getId().getNombreDeposito());
				itemBestSellerJSON.setPosicion(posicion);
				posicion++;
				itemsJSON.add(itemBestSellerJSON);
			}
			
			bestSeller.setRanking(itemsJSON);
			
			@SuppressWarnings("unchecked")
			List<Modulo> portales = em.createQuery("Select modulo from Modulo modulo where modulo.tipoModulo =:tipoModulo").setParameter("tipoModulo", TipoModulo.PortalWeb).getResultList();
			
			if(portales != null){
				
				URL url;
				HttpURLConnection urlConnection;
				ObjectMapper mapper;
				String jsonInString;
				StringBuffer response;
				URLConnection connection;
				RecepcionBestSellerJSON recepcionBestSellerJSON;
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
					
					urlConnection.getOutputStream().write(jsonInString.getBytes());; // Envío de un string en formato Json
					
					InputStream respuestaPrueba= urlConnection.getInputStream();
					
					//TODO
					
					//Falta mandar el JSON a cada uno y recibir la respuesta como RecepcionBestSellerJSON
					//Luego mandar eso como return del Metodo
					
					
					return null;
				}
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

}
