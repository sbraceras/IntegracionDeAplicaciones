package com.logistica.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.logistica.enums.TipoModulo;

@Entity
@Table(name = "Modulos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class Modulo {

	//Segun como fue conversado el identificador unico de un modulo no es el id sino el nombre
	//Por ese motivo ahora el nombre del modulo es su nueva pk
	
	@Id
	@Column(name = "nombreModulo")
	protected String nombre;
//	protected int id;
	protected String ip;
	protected TipoModulo tipoModulo;

	public Modulo() {

	}

	public Modulo(int id, String ip, TipoModulo tipoModulo, String nombre) {
		this.ip = ip;
		this.tipoModulo = tipoModulo;
		this.nombre = nombre;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public TipoModulo getTipoModulo() {
		return tipoModulo;
	}

	public void setTipoModulo(TipoModulo tipoModulo) {
		this.tipoModulo = tipoModulo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
