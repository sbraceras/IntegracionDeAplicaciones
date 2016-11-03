package com.logistica.sessionBeans.examples;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import com.logistica.ejb.StatefulLogisticaCalcServerLocal;
import com.logistica.ejb.StatefulLogisticaCalcServerRemote;

/**
 * Session Bean implementation class StatefulLogisticaCalcServer
 */
@Stateful
@LocalBean
public class StatefulLogisticaCalcServer implements StatefulLogisticaCalcServerRemote, StatefulLogisticaCalcServerLocal {

    /**
     * Default constructor. 
     */
    public StatefulLogisticaCalcServer() {
    }
    
    private int value = 0;
    public void add(int n) { value += n;}
    public void substract(int n) { value -= n;}
    public int getValue() { return value;}

}
