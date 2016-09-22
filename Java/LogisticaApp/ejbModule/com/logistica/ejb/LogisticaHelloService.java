package com.logistica.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.logistica.entity.Tarea;

/**
 * Session Bean implementation class LogisticaHelloService
 */
@Stateless
@LocalBean
public class LogisticaHelloService implements LogisticaHelloServiceRemote, LogisticaHelloServiceLocal {

	@PersistenceContext(unitName="MyPU")
	 private EntityManager manager;

    /**
     * Default constructor. 
     */
    public LogisticaHelloService() {
        // TODO Auto-generated constructor stub
    }
    
    public String sayHello(String name) {
    	return "Hello " + name;
    }
    
    public void nuevaTarea(){
    	Tarea tarea = new Tarea("Hola nueva tarea");
    	try{
    		manager.persist(tarea);
    	}catch (Exception e){
    		e.printStackTrace();
    		System.out.println("No funciono!");
    	}
    }


}
