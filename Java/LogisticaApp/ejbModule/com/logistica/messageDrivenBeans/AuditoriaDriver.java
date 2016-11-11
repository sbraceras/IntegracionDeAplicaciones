package com.logistica.messageDrivenBeans;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistica.dtos.LogDTO;
import com.logistica.enums.TipoModulo;
import com.logistica.jsons.LogJSON;
import com.logistica.sessionBeans.AdministradorAuditoria;

/**
 * Message-Driven Bean implementation class for: AuditoriaDriver
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jboss/exported/jms/queue/eventoAuditoria"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "java:/jboss/exported/jms/queue/eventoAuditoria")
public class AuditoriaDriver implements MessageListener {

	@EJB
	AdministradorAuditoria aa;

	/**
	 * Default constructor.
	 */
	public AuditoriaDriver() {
		
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

			Logger.getAnonymousLogger().info("Mensaje recibido: " + messageText);

			ObjectMapper mapper = new ObjectMapper();
			LogJSON jsonObject = mapper.readValue(messageText, LogJSON.class);

			LogDTO logDTO = new LogDTO();

			logDTO.setFecha(jsonObject.getFecha());

			if (jsonObject.getTipo().equals("Portal")) {
				logDTO.setTipoModulo(TipoModulo.PortalWeb);
			} else if (jsonObject.getTipo().equals("Despacho")) {
				logDTO.setTipoModulo(TipoModulo.Despacho);
			} else if (jsonObject.getTipo().equals("Deposito")) {
				logDTO.setTipoModulo(TipoModulo.Deposito);
			} else {
				logDTO.setTipoModulo(null);
			}

			logDTO.setNombreModulo(jsonObject.getModulo());
			logDTO.setDescripcion(jsonObject.getDescripcion());

			aa.guardarLog(logDTO);

		} catch (Exception e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e);
			e.printStackTrace();
		}
	}

}
