package com.logistica.ejb;

import javax.ejb.Remote;

@Remote
public interface LogisticaHelloServiceRemote {
	
	public String sayHello(String name);
	
	public void nuevaTarea();


}
