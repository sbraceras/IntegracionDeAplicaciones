package com.logistica.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

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
