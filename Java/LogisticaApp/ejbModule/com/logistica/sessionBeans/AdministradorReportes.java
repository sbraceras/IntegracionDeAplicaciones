package com.logistica.sessionBeans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.logistica.entityBeans.Articulo;
import com.logistica.jsons.BestSellerJSON;

@Stateless
@LocalBean
public class AdministradorReportes implements AdministradorReportesRemote, AdministradorReportesLocal {

	@PersistenceContext(unitName="MyPersistenceUnit")
	private EntityManager em;
	
	
	public BestSellerJSON enviarReporteBestSeller(){
		
		//Levanto los 10 Productos mas vendidos
		
		@SuppressWarnings("unchecked")
		List<Articulo> articulosVendidos = em.createQuery("Select articulo from Articulo articulo order by articulo.ventasAcumuladas desc").setFirstResult(0).setMaxResults(10).getResultList();
		
		if(articulosVendidos != null){
			return null;
		}
		else
		{
			//Habria que lanzar una exception
			return null;
		}
	}

}
