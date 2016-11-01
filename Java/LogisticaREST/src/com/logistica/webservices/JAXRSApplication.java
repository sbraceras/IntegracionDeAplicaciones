package com.logistica.webservices;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// CREACIÓN DEL ENDPOINT REST

/*
	- Debe registrarse el servlet que atiende el pedido HTTP
	- 'RestEasy' provee una clase para hacerlo automáticamente (Application)
*/
@ApplicationPath("/rest") // URL donde está localizado el servlet para procesar los pedidos HTTP
public class JAXRSApplication extends Application {
	// La clase 'Application' inserta y configura el servlet, que redirecciona a la clase con el servicio implementado (JAXRSExample)
}
