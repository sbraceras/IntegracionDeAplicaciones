package entity;

import dtos.ClienteDTO;

public class Cliente {
	private int id;
	private String nombre;
	private String apellido;
	private int dni;
	private String mail;
	private Direccion direccion;
	
	
	public Cliente(int id, String nombre, String apellido, int dni,
			String mail, Direccion direccion) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.mail = mail;
		this.direccion = direccion;
	}


	public Cliente() {
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


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public int getDni() {
		return dni;
	}


	public void setDni(int dni) {
		this.dni = dni;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public Direccion getDireccion() {
		return direccion;
	}


	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	
	public ClienteDTO toDTO(){
		
		ClienteDTO dto = new ClienteDTO();
		dto.setApellido(this.apellido);
		dto.setDireccion(this.direccion.toDTO());
		dto.setDni(this.dni);
		dto.setMail(this.mail);
		dto.setNombre(this.nombre);
		return dto;
	}
	
}
