package com.logistica.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.logistica.dto.ClienteDTO;

@Entity
@Table(name="Clientes")
public class Cliente {
	//Para que nos afecte que un cliente este registrado en diferentes
	//Portales, lo que hacemos es tener el dni como pk para tenerlo por
	//Unica vez, y cada vez que recibimos una venta actualizamos sus
	//Coordenadas
	@Id
	private int dni;
//	private int id;
	private String nombre;
	private String apellido;
	private String mail;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="idCliente")
	private Direccion direccion;
	
	
	public Cliente(int id, String nombre, String apellido,
			String mail, Direccion direccion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.mail = mail;
		this.direccion = direccion;
	}


	public Cliente() {
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
	
	public Cliente fromDTO (ClienteDTO dto) {
	
		// Cliente: nombre, apellido, latidud, longitud.
		
		Cliente cliente = new Cliente();
		Direccion direccion = new Direccion();
		Coordenada coordenada = new Coordenada();
		cliente.setNombre(dto.getNombre());
		cliente.setApellido(dto.getApellido());
		cliente.setDni(dto.getDni());
		coordenada.setLatitud(dto.getDireccion().getCoordenada().getLatitud());
		coordenada.setLongitud(dto.getDireccion().getCoordenada().getLongitud());
		direccion.setCoordenada(coordenada);
		cliente.setDireccion(direccion);
		return cliente;
	}
	
}
