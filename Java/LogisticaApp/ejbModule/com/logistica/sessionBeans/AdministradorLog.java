package com.logistica.sessionBeans;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.logistica.dtos.ItemVentaDTO;
import com.logistica.dtos.LogDTO;
import com.logistica.entityBeans.Despacho;
import com.logistica.entityBeans.Estandar;
import com.logistica.entityBeans.Log;
import com.logistica.enums.TipoLog;

/**
 * Session Bean implementation class AdministradorLog
 */
@Stateless
@LocalBean
public class AdministradorLog {

	@PersistenceContext(unitName="MyPersistenceUnit")
	private EntityManager em;
	private static String busquedaModulosDepositos = "SELECT d FROM Despacho d WHERE d.nombre LIKE :descripcionDespacho";
	private static String busquedaModulosEstandars = "SELECT e FROM Estandar e WHERE e.nombre LIKE :descripcionEstandar";
	private static String busquedaLogs = "SELECT l FROM Log l";
	
    /**
     * Default constructor. 
     */
    public AdministradorLog() {
    	
    }
    
    public void guardarLog(LogDTO logDTO){
    	Log log = new Log();
    	log.setDescripcion(logDTO.getDescripcion());
    	log.setFecha(logDTO.getFecha());
    	log.setTipo(TipoLog.OK);
    	List<Despacho> despachos = new ArrayList<Despacho>();
    	List<Estandar> estandars = new ArrayList<Estandar>();
    	despachos = this.buscarDespachos(logDTO.getNombreModulo());
    	estandars = this.buscarEstandar(logDTO.getNombreModulo());
		if(despachos.size() > 0){
			Despacho despacho = despachos.get(0);
			log.setModulo(despacho);
			em.persist(log);
		}else{
			if(estandars.size() > 0){
				Estandar estandar = estandars.get(0);
				log.setModulo(estandar);
				em.persist(log);
			}
		}
    }
    
    public List<LogDTO> buscarLogs(){
    	List<Log> logs = this.buscarLogsQuery();
    	List<LogDTO> logsDTO = new ArrayList<LogDTO>();
		for (Log log : logs) {
			LogDTO logDTO = log.toDTO();
			logsDTO.add(logDTO);
		}
		return logsDTO;
    }
    
    @SuppressWarnings("unchecked")
	public List<Despacho> buscarDespachos(String descripcionDespacho){
     return em.createQuery(
    		 busquedaModulosDepositos)
     .setParameter("descripcionDespacho", descripcionDespacho)
     .getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Estandar> buscarEstandar(String descripcionEstandar){
     return em.createQuery(
    		 busquedaModulosEstandars)
     .setParameter("descripcionEstandar", descripcionEstandar)
     .setMaxResults(1)
     .getResultList();
    }

    @SuppressWarnings("unchecked")
   	public List<Log> buscarLogsQuery(){
        return em.createQuery(
       		 busquedaLogs)
        .getResultList();
       }
    
}
