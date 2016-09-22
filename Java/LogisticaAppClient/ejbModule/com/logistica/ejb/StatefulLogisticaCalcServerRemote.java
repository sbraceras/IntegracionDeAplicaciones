package com.logistica.ejb;

import javax.ejb.Remote;

@Remote
public interface StatefulLogisticaCalcServerRemote {
	
	public void add(int n);
	public void substract(int n);
	public int getValue();


}
