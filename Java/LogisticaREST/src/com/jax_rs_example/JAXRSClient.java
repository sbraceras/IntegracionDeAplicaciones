package com.jax_rs_example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JAXRSClient {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://localhost:8080/LogisticaREST/rest/service/hola");

			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

			if(urlConnection.getResponseCode() != 200) {
				throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
			}

			String response = IOUtils.toString(urlConnection.getInputStream());
			System.out.println("Respuesta: " + response);

			// PRUEBA DE PETICION 'POST'
			url = new URL("http://localhost:8080/LogisticaREST/rest/service/saludo");

			urlConnection = (HttpURLConnection) url.openConnection();

			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type", "text/plain");

			IOUtils.write("Braceras y Masaro", urlConnection.getOutputStream());

			if (urlConnection.getResponseCode() != 200) {
				throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
			}

			response = IOUtils.toString(urlConnection.getInputStream());
			System.out.println("Respuesta: " + response);

			// PRUEBA DE GET CON PARAMETROS!
			url = new URL("http://localhost:8080/LogisticaREST/rest/service/BuenosAires?addressParameter=Lima717");

			urlConnection = (HttpURLConnection) url.openConnection();

			if(urlConnection.getResponseCode() != 200) {
				throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
			}

			response = IOUtils.toString(urlConnection.getInputStream());
			System.out.println("Respuesta: " + response);


			// Ahora, probamos sin enviar el 'addressParameter'. Deberia imprimir 'Unknown address'
			url = new URL("http://localhost:8080/LogisticaREST/rest/service/BuenosAires");

			urlConnection = (HttpURLConnection) url.openConnection();

			if(urlConnection.getResponseCode() != 200) {
				throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
			}

			response = IOUtils.toString(urlConnection.getInputStream());
			System.out.println("Respuesta: " + response);


			// REST con JSON (POST)
			url = new URL("http://localhost:8080/LogisticaREST/rest/service/persona");

			urlConnection = (HttpURLConnection) url.openConnection();

			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type", "application/json");

			IOUtils.write("{\"id\" : \"1759\" , \"nombre\" : \"John Doe\" }", urlConnection.getOutputStream()); // Envío de un string en formato Json

			response = IOUtils.toString(urlConnection.getInputStream()); // Recibe un string en formato Json
			System.out.println("Respuesta: " + response);

/*

			// ALTERNATIVA para REST con JSON (POST)
			// Podemos crear objeto Persona y serializarlo a un string Json para enviar en el POST
			Persona p = new Persona(1759, "John Doe", "");

			urlConnection = (HttpURLConnection) url.openConnection();

			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type", "application/json");


			IOUtils.write(p.getJsonString(), urlConnection.getOutputStream());

			// Crear objeto Persona a partir del string Json recibido en el response
			response = IOUtils.toString(urlConnection.getInputStream());

			Persona p2 = Persona.getPersonaFromJSON(response);
			System.out.println("Objeto Enviado: " + p2.getId() + " - " + p2.getNombre() + " - " + p2.getDireccion());

*/

			//ALTERNATIVA JACKSON
			
			ObjectMapper mapper = new ObjectMapper();
			Persona p = new Persona(1759, "John Doe", "");
			String jsonInString = mapper.writeValueAsString(p);
			System.out.println(jsonInString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
