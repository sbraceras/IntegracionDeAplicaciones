package com.logistica.webservices;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// CREACI�N DEL ENDPOINT REST

/*
	- Debe registrarse el servlet que atiende el pedido HTTP
	- 'RestEasy' provee una clase para hacerlo autom�ticamente (Application)
*/
@ApplicationPath("/rest") // URL donde est� localizado el servlet para procesar los pedidos HTTP
public class JAXRSApplication extends Application {
	// La clase 'Application' inserta y configura el servlet, que redirecciona a la clase con el servicio implementado (JAXRSExample)
}
