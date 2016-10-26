package com.example;

import java.io.StringReader;
import java.io.StringWriter;

//	import javax.json.*;

public class Persona {

	private int id;
	private String nombre;
	private String direccion;

	public Persona() {

	}

	public Persona(int id, String nombre, String direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

/*

	public String getJsonString() {
		JsonObject personObject = Json.createObjectBuilder()
				.add("id", this.getId())
				.add("nombre", this.getNombre())
				.add("direccion", this.getDireccion())
				.build();

		StringWriter stringWriter = new StringWriter();
		JsonWriter writer = Json.createWriter(stringWriter);
		writer.writeObject(personObject);
		writer.close();

		return stringWriter.getBuffer().toString();
	}

	public static Persona getPersonaFromJSON(String jsonString) {
		JsonReader reader = Json.createReader(new StringReader(jsonString));

		JsonObject personObject = reader.readObject();
		reader.close();

		Persona p = new Persona();
		p.setId(personObject.getInt("id"));
		p.setNombre(personObject.getString("nombre"));
		p.setDireccion(personObject.getString("direccion"));

		return p;
	}

*/

}
