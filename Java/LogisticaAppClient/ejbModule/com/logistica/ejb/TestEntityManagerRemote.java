package com.logistica.ejb;

import javax.ejb.Remote;

@Remote
public interface TestEntityManagerRemote {

	public void nuevoArticulo();

}
