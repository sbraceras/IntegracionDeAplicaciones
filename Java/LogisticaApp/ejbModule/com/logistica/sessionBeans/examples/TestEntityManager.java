package com.logistica.sessionBeans.examples;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.logistica.entityBeans.Articulo;
import com.logistica.entityBeans.IdArticulo;
import com.logistica.interfaces.TestEntityManagerLocal;
import com.logistica.interfaces.TestEntityManagerRemote;

/**
 * Session Bean implementation class TestEntityManager
 */
@Stateless
@LocalBean
public class TestEntityManager implements TestEntityManagerRemote, TestEntityManagerLocal {

	@PersistenceContext(unitName = "MyPersistenceUnit")
	private EntityManager manager;

	/**
	 * Default constructor.
	 */
	public TestEntityManager() {
		
	}

	public void nuevoArticulo() {
		IdArticulo idArticulo = new IdArticulo();
		idArticulo.setId(100);
		idArticulo.setNombreDeposito("G06");

		Articulo articulo  = new Articulo(idArticulo, (float) 58.95, "MOCHILA KARPATOS 2KG", 34);

		idArticulo.setId(101);
		idArticulo.setNombreDeposito("G12");

		Articulo articulo2 = new Articulo(idArticulo, (float) 99.17, "CARTUCHERA BOMBUCHA", 12);

		try {
			manager.persist(articulo);
			manager.persist(articulo2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
