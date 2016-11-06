package com.logistica.sessionBeans;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.logistica.dtos.LogDTO;
import com.logistica.entityBeans.Despacho;
import com.logistica.entityBeans.Estandar;
import com.logistica.entityBeans.Log;
import com.logistica.enums.TipoModulo;
import com.logistica.interfaces.AdministradorLogLocal;
import com.logistica.interfaces.AdministradorLogRemote;

/**
 * Session Bean implementation class AdministradorLog
 */
@Stateless
@LocalBean
public class AdministradorLog implements AdministradorLogRemote, AdministradorLogLocal {

	@PersistenceContext(unitName="MyPersistenceUnit")
	private EntityManager em;
	private static String DESPACHO = "DESPACHO";
	private static String DEPOSITO = "DEPOSITO";
	private static String PORTAL = "PORTAL";
	private static String busquedaModulosDepositos = "SELECT d FROM Despacho d WHERE d.nombre LIKE :descripcionDespacho";
	private static String busquedaModulosEstandars = "SELECT e FROM Estandar e WHERE e.nombre LIKE :descripcionEstandar";
	
    /**
     * Default constructor. 
     */
    public AdministradorLog() {
    	
    }
    
    public void guardarLog(LogDTO logDTO) throws ParseException{
    	Log log = new Log();
    	DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
    	Date fechaLog = new Date();
    	log.setDescripcion(logDTO.getDescripcion());
    	log.setFecha(fechaLog);
    	List<Despacho> despachos = new ArrayList<Despacho>();
    	List<Estandar> estandars = new ArrayList<Estandar>();
    	despachos = this.buscarDespachos(logDTO.getNombreModulo());
    	estandars = this.buscarEstandar(logDTO.getNombreModulo());
    	if(despachos.isEmpty() && estandars.isEmpty()){
    		if(logDTO.getNombreModulo().contains(DESPACHO)){
    			Despacho despacho = new Despacho();
    			despacho.setDescripcion(logDTO.getNombreModulo());
    			despacho.setTipoModulo(TipoModulo.Despacho);
    			despacho.setNombre(logDTO.getNombreModulo());
    			em.persist(despacho);
    			log.setModulo(despacho);
    			em.persist(log);
    			//return "Funciono";
    		}else{
    			if(logDTO.getNombreModulo().contains(DEPOSITO)){
    				Estandar estandar = new Estandar();
    				estandar.setTipoModulo(TipoModulo.Deposito);
    				estandar.setNombre(logDTO.getNombreModulo());
        			em.persist(estandar);
        			log.setModulo(estandar);
        			em.persist(log);
    			}else{
    				if(logDTO.getNombreModulo().contains(PORTAL)){
    					Estandar estandar = new Estandar();
        				estandar.setTipoModulo(TipoModulo.PortalWeb);
        				estandar.setNombre(logDTO.getNombreModulo());
            			em.persist(estandar);
            			log.setModulo(estandar);
            			em.persist(log);
    				}
    			}
    		}
    	}
    	//return "Falso";
    }
    
    public List<Despacho> buscarDespachos(String descripcionDespacho){
     return em.createQuery(
    		 busquedaModulosDepositos)
     .setParameter("descripcionDespacho", descripcionDespacho)
     .getResultList();
    }
    
    public List<Estandar> buscarEstandar(String descripcionEstandar){
     return em.createQuery(
    		 busquedaModulosEstandars)
     .setParameter("descripcionEstandar", descripcionEstandar)
     .setMaxResults(1)
     .getResultList();
    }

    
}
