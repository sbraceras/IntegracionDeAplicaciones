package com.example;

import javax.ws.rs.*;

// El Servidor recibe el Request HTTP, lo procesa (como cualquier pedido HTTP) y envía Response HTTP.
// El Response HTTP puede contener la información en distintos formatos (texto plano, XML, CSV, JSON, etc.)

@Path("/service") // URL donde están localizados los servicios
public class JAXRSExample {

	@GET // Indica que el método procesa requests GET
	@Path("/hola") // URL del servicio específico
	@Produces({ "text/plain" }) // Indica que el response contiene texto plano
	public String hola() { // Método implementando la lógica de negocios
		return "Hola!";
	}

	@POST
	@Path("/saludo")
	@Produces({ "text/plain" })
	public String saludo(String nombre) {
		return "Hola " + nombre;
	}

	@GET
	@Path("/{cityParameter}") // URL donde está localizado el servicio
	public String responseMsg(
			@PathParam("cityParameter") String city,
			@DefaultValue("Unknown address")// Valor si el parámetro no es suministrado
			@QueryParam("addressParameter")	// Nombre del parámetro en la URL
				String address) {
		return "Hello from " + city + " at " + address;
	}

	// REST con JSON (POST)
	@POST
	@Path("/persona")
	@Consumes({ "application/json" })	// Indica que consume (recibe) un objeto Json en el POST. Lo parsea a un objeto de tipo Persona
	@Produces({ "application/json" })	// Indica que produce (envía) un response con un objeto Json incluido. 
										// Toma el objeto Persona (p) y lo serializa en un string Json
	public Persona asignarDireccion(Persona persona) {
		persona.setDireccion("Lima 717");

		return persona;
	}

}
