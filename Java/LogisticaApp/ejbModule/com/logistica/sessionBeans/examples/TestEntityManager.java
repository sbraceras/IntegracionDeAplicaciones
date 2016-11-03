package com.logistica.sessionBeans.examples;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.logistica.ejb.TestEntityManagerLocal;
import com.logistica.ejb.TestEntityManagerRemote;
import com.logistica.entityBeans.Articulo;

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
		Articulo articulo  = new Articulo(100, (float) 58.95, "MOCHILA KARPATOS 2KG", 34);
		Articulo articulo2 = new Articulo(101, (float) 99.17, "CARTUCHERA BOMBUCHA", 12);
		try {
			manager.persist(articulo);
			manager.persist(articulo2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
