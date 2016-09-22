package com.logistica.ejb;

import javax.ejb.Local;

@Local
public interface LogisticaHelloServiceLocal {
	
	public String sayHello(String name);
	
	public void nuevaTarea();


}
