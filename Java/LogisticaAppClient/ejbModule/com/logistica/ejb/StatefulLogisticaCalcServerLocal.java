package com.logistica.ejb;

import javax.ejb.Local;

@Local
public interface StatefulLogisticaCalcServerLocal {
	
	public void add(int n);
	public void substract(int n);
	public int getValue();


}
