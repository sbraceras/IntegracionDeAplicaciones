package com.logistica.messageDrivenBeans;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.logistica.sessionBeans.AdministradorLog; // AuditoriaManager

/**
 * Message-Driven Bean implementation class for: AuditoriaDriver
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/ColaAuditoria"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "java:/jms/queue/ColaAuditoria")
public class AuditoriaDriver implements MessageListener {

	@EJB
	AdministradorLog al;

	/**
	 * Default constructor.
	 */
	public AuditoriaDriver() {
		// TODO Auto-generated constructor stub
	}

	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	
    	try {
    		String messageText = null;

    		if (message instanceof TextMessage) {
    			messageText = ((TextMessage) message).getText();
    		}

//    		Logger.getAnonymousLogger().info("Mensaje recibido: " + messageText);

    		System.out.println("Mensaje recibido: " + messageText);

//    		al.guardarLog(logDTO); // DEBERIAMOS CONVERTIR 'messageText' de JSON a DTO? ANALIZAR

    	} catch (Exception e) {
//    		Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e);
    		e.printStackTrace();
    	}

    }

}
