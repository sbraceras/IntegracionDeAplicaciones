package com.logistica.test;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

// Cliente JMS (Productor)
public class ProducerConsola {

	public static void main(String[] args) throws NamingException, JMSException {
		final Properties properties = new Properties();

		properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		properties.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080"); // aca debe ponerse la IP donde va a estar la Queue o Topic
		properties.put("jboss.naming.client.connect.timeout", "15000");

		Context namingContext = new InitialContext(properties);

		ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup("jms/RemoteConnectionFactory");
		Destination destination = (Destination) namingContext.lookup("/jms/queue/eventoAuditoria"); // Siendo Cliente, le quitamos 'java:/jboss/exported'

		// Crear JMS connection, session, producer
		Connection connection = connectionFactory.createConnection("guest", "guest"); // Application User de JBOSS (usando add-user)
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		connection.start();
		// crear un producer para enviar mensajes usando la session
		MessageProducer producer = session.createProducer(destination);
		// crear un mensaje de tipo text y setearle el contenido
		TextMessage message = session.createTextMessage();

		message.setText(
			"{" +
					"\"fecha\": \"2002-05-30T09:00:00\"," +
					"\"tipo\": \"Portal\"," +
					"\"modulo\": \"G10\"," +
					"\"descripcion\": \"Se detectó actividad sospechosa...\"" +
			"}"
		);

		// enviar el mensaje
		producer.send(message);

		connection.close();
	}

}
