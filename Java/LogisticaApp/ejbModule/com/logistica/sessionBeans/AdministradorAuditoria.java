package com.logistica.sessionBeans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.logistica.dtos.LogDTO;
import com.logistica.entityBeans.Articulo;
import com.logistica.entityBeans.Despacho;
import com.logistica.entityBeans.Estandar;
import com.logistica.entityBeans.Log;
import com.logistica.enums.TipoLog;

/**
 * Session Bean implementation class AdministradorAuditoria
 */
@Stateless
@LocalBean
public class AdministradorAuditoria {

	@PersistenceContext(unitName = "MyPersistenceUnit")
	private EntityManager em;

	private static String busquedaModulosDepositos = "SELECT d FROM Despacho d WHERE d.nombre LIKE :descripcionDespacho";
	private static String busquedaModulosEstandars = "SELECT e FROM Estandar e WHERE e.nombre LIKE :descripcionEstandar";
	private static String busquedaLogs = "SELECT l FROM Log l ORDER BY l.id DESC";
//	private static String busquedaLogs = "SELECT l FROM Log l ORDER BY l.fecha DESC";

	/**
	 * Default constructor.
	 */
	public AdministradorAuditoria() {

	}

	public void guardarLog(LogDTO logDTO) throws Exception {
		Log log = new Log();
		log.setDescripcion(logDTO.getDescripcion());
		log.setFecha(logDTO.getFecha());
		log.setTipo(TipoLog.OK); // OJO, HABLAR CON GRUPOS PARA CAMBIAR EL JSON. DEBERIA LLEGAR OK y KO !
		List<Despacho> despachos = new ArrayList<Despacho>();
		List<Estandar> estandars = new ArrayList<Estandar>();
		despachos = this.buscarDespachos(logDTO.getNombreModulo());
		estandars = this.buscarEstandar(logDTO.getNombreModulo());
		if (despachos.size() > 0) {
			Despacho despacho = despachos.get(0); // SIEMPRE DEBERIA OBTENER UNO SOLO PORQUE EL 'NombreModulo' ES UNIVOCO
			log.setModulo(despacho);
			em.persist(log);
		} else {
			if (estandars.size() > 0) {
				Estandar estandar = estandars.get(0); // SIEMPRE DEBERIA OBTENER UNO SOLO PORQUE EL 'NombreModulo' ES UNIVOCO
				log.setModulo(estandar);
				em.persist(log);
			} else {
				// NO HAY MODULO ASOCIADO CON ESE 'NombreModulo'
				throw new Exception("No se puede guardar el Log ya que el módulo \"" + logDTO.getNombreModulo() + 
						"\" no está registrado en el sistema");
			}
		}
	}

	public List<LogDTO> buscarLogs() {
		List<Log> logs = this.buscarLogsQuery();
		List<LogDTO> logsDTO = new ArrayList<LogDTO>();
		for (Log log : logs) {
			LogDTO logDTO = log.toDTO();
			logsDTO.add(logDTO);
		}
		return logsDTO;
	}

	@SuppressWarnings("unchecked")
	public List<Despacho> buscarDespachos(String descripcionDespacho) {
		return em.createQuery(busquedaModulosDepositos).setParameter("descripcionDespacho", descripcionDespacho)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Estandar> buscarEstandar(String descripcionEstandar) {
		return em.createQuery(busquedaModulosEstandars).setParameter("descripcionEstandar", descripcionEstandar)
				.setMaxResults(1).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Log> buscarLogsQuery() {
		return em.createQuery(busquedaLogs).setFirstResult(0).setMaxResults(10).getResultList();
	}

}
