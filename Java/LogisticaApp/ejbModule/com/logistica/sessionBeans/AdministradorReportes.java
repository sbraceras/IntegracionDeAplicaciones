package com.logistica.sessionBeans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.logistica.dtos.BestSellerDTO;
import com.logistica.dtos.EstandarDTO;
import com.logistica.dtos.ItemBestSellerDTO;
import com.logistica.dtos.RecepcionBestSellerDTO;
import com.logistica.dtos.ResumenPortalDTO;
import com.logistica.dtos.VentaDTO;
import com.logistica.entityBeans.Articulo;
import com.logistica.entityBeans.Estandar;
import com.logistica.entityBeans.IDVenta;
import com.logistica.entityBeans.Modulo;
import com.logistica.entityBeans.Venta;
import com.logistica.enums.TipoModulo;
import com.logistica.jsons.BestSellerJSON;
import com.logistica.jsons.ItemBestSellerJSON;
import com.logistica.jsons.RecepcionBestSellerJSON;
import com.logistica.utils.RESTManager;

@Stateless
@LocalBean
public class AdministradorReportes {

	@PersistenceContext(unitName = "MyPersistenceUnit")
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<RecepcionBestSellerDTO> enviarReporteBestSeller() throws Exception {
		// LEVANTO LOS 10 PRODUCTOS MAS VENDIDOS
		BestSellerJSON bestSeller = generarBestSellerJSON();

		if (bestSeller != null) {
			List<Modulo> portales = em.createQuery("Select modulo from Modulo modulo where modulo.tipoModulo = :tipoModulo").setParameter("tipoModulo", TipoModulo.PortalWeb).getResultList();

			List<RecepcionBestSellerDTO> estadoRecepciones = new ArrayList<RecepcionBestSellerDTO>();

			if (portales != null) {

				for (Modulo modulo: portales) {
					if(((Estandar) modulo).getUrlEnvioRankingBestSellers() != null){
						RecepcionBestSellerJSON recepcionPortal = (RecepcionBestSellerJSON) RESTManager.sendPOST(
							"http://" + modulo.getIp() + ":8080/" + ((Estandar) modulo).getUrlEnvioRankingBestSellers(), bestSeller, RecepcionBestSellerJSON.class);
	
				        // Lo paso a DTO para mandar la respuesta al sitio web!
				        RecepcionBestSellerDTO recepcionPortalDTO = new RecepcionBestSellerDTO();
				        
				        if(recepcionPortal != null){
					        recepcionPortalDTO.setEstado(recepcionPortal.getEstado());
					        recepcionPortalDTO.setMensaje(recepcionPortal.getMensaje());
				        }else{
				        	recepcionPortalDTO.setEstado("ERROR");
				        	recepcionPortalDTO.setMensaje("ERROR");
				        }
				        recepcionPortalDTO.setNombrePortal(modulo.getNombre());
	
				        estadoRecepciones.add(recepcionPortalDTO);	
					}
				}

				// Devuelvo el resultado de las Recepciones
				return estadoRecepciones;

			} else {
				throw new Exception("No se encontró ningún Portal Web");
			}
		} else {
			return null;
		}
	}

	public BestSellerDTO generarReporteBestSeller() {
		@SuppressWarnings("unchecked")
		List<Articulo> articulosVendidos = em.createQuery("Select articulo from Articulo articulo order by articulo.ventasAcumuladas desc").setFirstResult(0).setMaxResults(10).getResultList();
		
		BestSellerDTO bestSeller = new BestSellerDTO();
		
		List<ItemBestSellerDTO> itemsDTO = new ArrayList<ItemBestSellerDTO>();
		ItemBestSellerDTO itemBestSellerDTO;
		int posicion = 1;
		for(Articulo articulo: articulosVendidos){
			itemBestSellerDTO = new ItemBestSellerDTO();

//			itemBestSellerJSON.setCodigo(articulo.getId().toString()); // Esto es si llegan a querer el codigo compuesto, pero en el Excel no se veia asi
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
	
	
	public List<VentaDTO> obtenerUltimasVentas() throws Exception{
		@SuppressWarnings("unchecked")
		List<Venta> ultimasVentas = em.createQuery("Select venta from Venta venta order by venta.fechaHora desc").setFirstResult(0).setMaxResults(10).getResultList();
		if(ultimasVentas != null){
			List<VentaDTO> devolver = new ArrayList<VentaDTO>();
			
			for(Venta venta: ultimasVentas){
				devolver.add(venta.toDTO());
			}
			
			return devolver;
		}
		else
		{
			throw new Exception("No hay Ventas en el Sistema");
		}
	}
	
	public List<ResumenPortalDTO> obtenerVentasPortal (){

		List<ResumenPortalDTO> resumenPortales = new ArrayList<ResumenPortalDTO>();
		float totalPortal;
		
		@SuppressWarnings("unchecked")
		List<Estandar> portalesWeb = em.createQuery("Select modulo From Modulo modulo where modulo.tipoModulo =:tipoModulo").setParameter("tipoModulo", TipoModulo.PortalWeb).getResultList();
		ResumenPortalDTO resumenPortal;
		EstandarDTO portalDTO;
		for(Estandar portal: portalesWeb){
			totalPortal = 0.0F;
			resumenPortal = new ResumenPortalDTO();
			portalDTO = new EstandarDTO();
			resumenPortal.setVentas(obtenerVentasPortal(portal));
			portalDTO.setNombre(portal.getNombre());
			resumenPortal.setPortal(portalDTO);
			
			for(VentaDTO venta: resumenPortal.getVentas()){
				totalPortal = totalPortal + venta.getMonto();
			}
			
			resumenPortal.setMontoTotal(totalPortal);
			
			resumenPortales.add(resumenPortal);
		}
		
		return resumenPortales;
	}
	
	
	private List<VentaDTO> obtenerVentasPortal (Estandar portal){
		
		
		@SuppressWarnings("unchecked")
		List<Venta> ventas = (List<Venta>) em.createQuery("Select venta from Venta venta where venta.id.modulo.nombre =:modulo").setParameter("modulo", portal.getNombre()).getResultList();
	
	
		List<VentaDTO> devolver = new ArrayList<VentaDTO>();
		
		for(Venta venta: ventas){
			devolver.add(venta.toDTO());
		}
		
		return devolver;
	}
	
	public VentaDTO obtenerVenta (VentaDTO dto){
		
		
		IDVenta id = new IDVenta();
		id.setId(dto.getId());
		Modulo modulo = buscarModuloPorNombre(dto.getNombrePortal());
		id.setModulo(modulo);
		
		Venta venta = em.find(Venta.class, id);
		
		if(venta != null){
			return venta.toDTO();
		}
		
		return null;
	}
	
	private Modulo buscarModuloPorNombre (String nombre){
		
		Modulo modulo =  (Modulo) em.createQuery("Select modulo from Modulo modulo where modulo.nombre =:nombre").setParameter("nombre", nombre).getSingleResult();
		if(modulo != null){
			return modulo;
		}
		
		else{
			return null;
		}
	}

}

