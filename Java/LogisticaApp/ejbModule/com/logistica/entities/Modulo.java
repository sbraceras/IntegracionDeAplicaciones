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

	@Id
	@Column(name = "idModulo")
	protected int id;
	protected String ip;
	protected TipoModulo tipoModulo;
	protected String nombre;

	public Modulo() {

	}

	public Modulo(int id, String ip, TipoModulo tipoModulo, String nombre) {
		this.id = id;
		this.ip = ip;
		this.tipoModulo = tipoModulo;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
